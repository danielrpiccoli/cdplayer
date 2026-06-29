package com.cdplayer.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.cdplayer.R

@Composable
fun TitleBar() {
    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .height(20.dp)
            .background(ImageProvider(R.drawable.title_bar_gradient))
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon + title (takes all remaining space, pushing buttons to the right)
        Row(
            modifier = GlanceModifier.defaultWeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(R.drawable.cd_player_icon),
                contentDescription = "CD Player",
                modifier = GlanceModifier.size(14.dp)
            )
            Spacer(modifier = GlanceModifier.width(5.dp))
            Text(
                text = "CD Player",
                style = TextStyle(
                    color = ColorProvider(R.color.white),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        // Win98 window control buttons
        Row(verticalAlignment = Alignment.CenterVertically) {
            WindowButton("_")
            Spacer(modifier = GlanceModifier.width(2.dp))
            WindowButton("□")
            Spacer(modifier = GlanceModifier.width(2.dp))
            WindowButton("×")
        }
    }
}

@Composable
private fun WindowButton(label: String) {
    Box(
        modifier = GlanceModifier
            .size(16.dp)
            .background(ImageProvider(R.drawable.button_border)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = ColorProvider(R.color.black),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
