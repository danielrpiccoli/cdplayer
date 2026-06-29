package com.cdplayer

const val PREFS_NAME = "cdplayer_prefs"
const val KEY_ARTIST = "artist"
const val KEY_TRACK = "track"
const val KEY_IS_PLAYING = "is_playing"
const val KEY_POSITION_MS = "position_ms"
const val KEY_DURATION_MS = "duration_ms"

data class PlayerState(
    val artist: String = "",
    val track: String = "",
    val isPlaying: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L
) {
    val progressFraction: Float
        get() = if (durationMs > 0) (positionMs.toFloat() / durationMs).coerceIn(0f, 1f) else 0f
}
