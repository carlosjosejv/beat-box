package com.greenshark.beatbox.models

/**
 * Created by Carlos Jiménez on 02-Nov-23.
 */
data class UserPreferences(
    val favorites: ArrayList<AudioFile> = arrayListOf(),
    val playlists: ArrayList<Playlist> = arrayListOf()
)

data class Playlist(val name: String, val tracks: List<AudioFile>)
