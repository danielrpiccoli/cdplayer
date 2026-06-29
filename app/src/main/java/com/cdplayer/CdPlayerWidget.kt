package com.cdplayer

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.unit.ColorProvider
import com.cdplayer.components.ControlButtons
import com.cdplayer.components.ProgressBar
import com.cdplayer.components.TitleBar
import com.cdplayer.components.TrackInfo

class CdPlayerWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val state = PlayerState(
            artist = prefs.getString(KEY_ARTIST, "") ?: "",
            track = prefs.getString(KEY_TRACK, "") ?: "",
            isPlaying = prefs.getBoolean(KEY_IS_PLAYING, false),
            positionMs = prefs.getLong(KEY_POSITION_MS, 0L),
            durationMs = prefs.getLong(KEY_DURATION_MS, 0L)
        )
        provideContent {
            PlayerScreen(state)
        }
    }
}

@Composable
fun PlayerScreen(state: PlayerState) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(R.color.silver))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(ImageProvider(R.drawable.window_border))
        ) {
            TitleBar()
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = GlanceModifier
                        .size(80.dp)
                        .background(ColorProvider(R.color.black))
                        .padding(2.dp)
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.night_in_question),
                        contentDescription = "Album Art",
                        modifier = GlanceModifier.fillMaxSize()
                    )
                }
                TrackInfo(
                    artist = state.artist,
                    track = state.track,
                    modifier = GlanceModifier.defaultWeight()
                )
            }
            ProgressBar(progressFraction = state.progressFraction)
            ControlButtons(isPlaying = state.isPlaying)
        }
    }
}
