package com.cdplayer

import android.content.ComponentName
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaMetadata
import android.media.session.MediaSessionManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

private const val TAG = "CDPlayer"

class MediaListenerService : NotificationListenerService() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d(TAG, "Listener connected")
        activeNotifications?.firstOrNull { it.packageName == "com.spotify.music" }
            ?.let { processNotification(it) }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.d(TAG, "Notification posted: ${sbn.packageName}")
        if (sbn.packageName != "com.spotify.music") return
        processNotification(sbn)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        if (sbn.packageName != "com.spotify.music") return
        saveAndUpdate(track = "", artist = "", album = "", isPlaying = false, albumArtPath = "")
    }

    private fun processNotification(sbn: StatusBarNotification) {
        val extras = sbn.notification.extras
        val track = extras.getCharSequence("android.title")?.toString() ?: return
        val artist = extras.getCharSequence("android.text")?.toString() ?: ""
        Log.d(TAG, "Processing Spotify: track=$track artist=$artist")

        val isPlaying = sbn.notification.actions?.any { action ->
            action.title?.toString()?.contains("pause", ignoreCase = true) == true
        } ?: false

        val album = extras.getCharSequence("android.subText")?.toString()
            ?.takeIf { it != "No Repeat" && it != "Repeat" && it != "Shuffle" } ?: ""

        val albumArtPath = sbn.notification.getLargeIcon()?.let { icon ->
            try {
                val drawable = icon.loadDrawable(this) ?: return@let null
                val w = drawable.intrinsicWidth.coerceAtLeast(1)
                val h = drawable.intrinsicHeight.coerceAtLeast(1)
                val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bmp)
                drawable.setBounds(0, 0, w, h)
                drawable.draw(canvas)
                val file = File(filesDir, "album_art.png")
                FileOutputStream(file).use { bmp.compress(Bitmap.CompressFormat.PNG, 90, it) }
                file.absolutePath
            } catch (e: Exception) {
                null
            }
        } ?: getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_ALBUM_ART_PATH, "") ?: ""

        val msm = getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
        val sessions = msm.getActiveSessions(ComponentName(this, MediaListenerService::class.java))
        val session = sessions.firstOrNull { it.packageName == "com.spotify.music" }
        val positionMs = session?.playbackState?.position ?: 0L
        val durationMs = session?.metadata?.getLong(MediaMetadata.METADATA_KEY_DURATION) ?: 0L

        saveAndUpdate(track, artist, album, isPlaying, albumArtPath, positionMs, durationMs)
    }

    private fun saveAndUpdate(
        track: String,
        artist: String,
        album: String,
        isPlaying: Boolean,
        albumArtPath: String,
        positionMs: Long = 0L,
        durationMs: Long = 0L
    ) {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
            .putString(KEY_TRACK, track)
            .putString(KEY_ARTIST, artist)
            .putString(KEY_ALBUM, album)
            .putBoolean(KEY_IS_PLAYING, isPlaying)
            .putString(KEY_ALBUM_ART_PATH, albumArtPath)
            .putLong(KEY_POSITION_MS, positionMs)
            .putLong(KEY_POSITION_TIMESTAMP, System.currentTimeMillis())
            .putLong(KEY_DURATION_MS, durationMs)
            .apply()

        Log.d(TAG, "saveAndUpdate called: track=$track")
        scope.launch {
            Log.d(TAG, "Coroutine launched")
            val manager = GlanceAppWidgetManager(this@MediaListenerService)
            val ids = manager.getGlanceIds(CdPlayerWidget::class.java)
            Log.d(TAG, "Glance IDs found: ${ids.size}")
            ids.forEach { id ->
                CdPlayerWidget().update(this@MediaListenerService, id)
                Log.d(TAG, "Updated glance id: $id")
            }
        }
    }
}
