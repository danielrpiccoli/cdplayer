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
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.unit.ColorProvider
import com.cdplayer.components.TitleBar
import com.cdplayer.components.TrackInfo

class CdPlayerWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            PlayerScreen()
        }
    }
}

@Composable
fun PlayerScreen() {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(R.color.silver))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = GlanceModifier
                .background(ImageProvider(R.drawable.window_border))
        ) {
            TitleBar()
            Row(
                modifier = GlanceModifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Album art with black border
                Box(
                    modifier = GlanceModifier
                        .size(90.dp)
                        .background(ColorProvider(R.color.black))
                        .padding(2.dp)
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.night_in_question),
                        contentDescription = "Album Art",
                        modifier = GlanceModifier.fillMaxSize()
                    )
                }
                TrackInfo()
            }
        }
    }
}
