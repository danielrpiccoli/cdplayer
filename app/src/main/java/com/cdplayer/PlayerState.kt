package com.cdplayer

const val PREFS_NAME = "cdplayer_prefs"
const val KEY_ARTIST = "artist"
const val KEY_TRACK = "track"
const val KEY_ALBUM = "album"
const val KEY_IS_PLAYING = "is_playing"
const val KEY_ALBUM_ART_PATH = "album_art_path"
const val KEY_POSITION_MS = "position_ms"
const val KEY_POSITION_TIMESTAMP = "position_timestamp"
const val KEY_DURATION_MS = "duration_ms"

data class PlayerState(
    val artist: String = "",
    val track: String = "",
    val album: String = "",
    val isPlaying: Boolean = false,
    val albumArtPath: String = "",
    val positionMs: Long = 0L,
    val positionTimestamp: Long = 0L,
    val durationMs: Long = 0L
) {
    val progressFraction: Float
        get() {
            if (durationMs <= 0) return 0f
            val estimated = if (isPlaying && positionTimestamp > 0)
                positionMs + (System.currentTimeMillis() - positionTimestamp)
            else positionMs
            return (estimated.toFloat() / durationMs).coerceIn(0f, 1f)
        }
}
