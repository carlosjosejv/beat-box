package com.greenshark.beatbox.models

/**
 * Created by Carlos Jiménez on 02-Nov-23.
 */
data class UserPreferences(
    val favorites: List<AudioFile> = emptyList(),
    val playlists: List<Playlist> = emptyList()
)

data class Playlist(val name: String, val tracks: List<AudioFile>)
