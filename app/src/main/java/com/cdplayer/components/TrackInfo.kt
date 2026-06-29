package com.cdplayer.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.defaultWeight
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
fun TrackInfo(artist: String, track: String) {
    Column(
        modifier = GlanceModifier
            .defaultWeight()
            .padding(start = 8.dp)
    ) {
        InfoRow(label = "Artist:", value = artist.ifBlank { "--" })
        Spacer(modifier = GlanceModifier.height(5.dp))
        InfoRow(label = "Track:", value = track.ifBlank { "--" })
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = ColorProvider(R.color.black),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = GlanceModifier.width(45.dp)
        )
        Box(
            modifier = GlanceModifier
                .defaultWeight()
                .height(18.dp)
                .background(ImageProvider(R.drawable.input_field_border))
                .padding(horizontal = 4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = value,
                style = TextStyle(
                    color = ColorProvider(R.color.black),
                    fontSize = 11.sp
                )
            )
        }
        Box(
            modifier = GlanceModifier
                .padding(start = 2.dp)
                .size(15.dp)
                .background(ImageProvider(R.drawable.button_border)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "▼",
                style = TextStyle(
                    color = ColorProvider(R.color.black),
                    fontSize = 8.sp
                )
            )
        }
    }
}
