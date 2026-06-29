package com.cdplayer.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import com.cdplayer.R

@Composable
fun ProgressBar(progressFraction: Float) {
    val widgetWidth = LocalSize.current.width
    val thumbWidth = 14.dp
    val trackWidth = widgetWidth - 16.dp - thumbWidth  // 8dp padding each side
    val leftSpace = maxOf(0.dp, trackWidth * progressFraction)
    val rightSpace = maxOf(0.dp, trackWidth * (1f - progressFraction))

    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Sunken track line
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(4.dp)
                .background(ImageProvider(R.drawable.progress_track))
        ) {}
        // Thumb positioned by left spacer width
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = GlanceModifier.width(leftSpace).height(4.dp)) {}
            Box(
                modifier = GlanceModifier
                    .width(thumbWidth)
                    .height(24.dp)
                    .background(ImageProvider(R.drawable.button_border))
            ) {}
            Box(modifier = GlanceModifier.width(rightSpace).height(4.dp)) {}
        }
    }
}
