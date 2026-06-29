package com.cdplayer

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MediaListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName != "com.spotify.music") return

        val extras = sbn.notification.extras
        val track = extras.getString("android.title") ?: return
        val artist = extras.getString("android.text") ?: ""

        // A pause action in the notification means Spotify is currently playing
        val isPlaying = sbn.notification.actions?.any { action ->
            action.title?.toString()?.contains("pause", ignoreCase = true) == true
        } ?: false

        saveAndUpdate(track = track, artist = artist, isPlaying = isPlaying)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        if (sbn.packageName != "com.spotify.music") return
        saveAndUpdate(track = "", artist = "", isPlaying = false)
    }

    private fun saveAndUpdate(track: String, artist: String, isPlaying: Boolean) {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
            .putString(KEY_TRACK, track)
            .putString(KEY_ARTIST, artist)
            .putBoolean(KEY_IS_PLAYING, isPlaying)
            .apply()

        val manager = AppWidgetManager.getInstance(this)
        val ids = manager.getAppWidgetIds(ComponentName(this, CdPlayerReceiver::class.java))
        if (ids.isNotEmpty()) {
            sendBroadcast(
                Intent(this, CdPlayerReceiver::class.java).apply {
                    action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                    putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                }
            )
        }
    }
}
