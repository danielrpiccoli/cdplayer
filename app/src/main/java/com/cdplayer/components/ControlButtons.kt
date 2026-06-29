package com.cdplayer.components

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionSendBroadcast
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.cdplayer.CdPlayerReceiver
import com.cdplayer.R

const val ACTION_PLAY_PAUSE = "com.cdplayer.ACTION_PLAY_PAUSE"
const val ACTION_NEXT = "com.cdplayer.ACTION_NEXT"
const val ACTION_PREVIOUS = "com.cdplayer.ACTION_PREVIOUS"
const val ACTION_STOP = "com.cdplayer.ACTION_STOP"

@Composable
fun ControlButtons(isPlaying: Boolean) {
    val context = LocalContext.current

    fun broadcast(action: String): Action =
        actionSendBroadcast(Intent(action).setClass(context, CdPlayerReceiver::class.java))

    Row(
        modifier = GlanceModifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlButton(label = "◄◄", action = broadcast(ACTION_PREVIOUS))
        Spacer(GlanceModifier.width(3.dp))
        ControlButton(label = "▶▶", action = broadcast(ACTION_NEXT))
        Spacer(GlanceModifier.width(3.dp))
        ControlButton(label = if (isPlaying) "⏸" else "▶", action = broadcast(ACTION_PLAY_PAUSE))
        Spacer(GlanceModifier.width(3.dp))
        ControlButton(label = "■", action = broadcast(ACTION_STOP))
        Spacer(GlanceModifier.width(3.dp))
        ControlButton(label = "●", action = broadcast(ACTION_PLAY_PAUSE))
    }
}

@Composable
private fun ControlButton(label: String, action: Action) {
    Box(
        modifier = GlanceModifier
            .size(26.dp)
            .background(ImageProvider(R.drawable.button_border))
            .clickable(action),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = ColorProvider(R.color.black),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
