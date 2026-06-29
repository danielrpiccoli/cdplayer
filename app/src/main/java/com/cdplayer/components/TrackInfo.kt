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
fun TrackInfo() {
    Column(modifier = GlanceModifier.padding(start = 10.dp)) {
        InfoRow(label = "Artist:", value = "TV Girl")
        Spacer(modifier = GlanceModifier.height(5.dp))
        InfoRow(label = "Track:", value = "The Desolation Tango")
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
        // Win98-style sunken input field
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
        // Dropdown arrow
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
