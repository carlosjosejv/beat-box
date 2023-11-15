package com.greenshark.beatbox.models

/**
 * Created by Carlos Jiménez on 02-Nov-23.
 */
data class UserFavorites(
    val favorites: ArrayList<AudioFile> = arrayListOf()
)

data class Playlist(val name: String, val tracks: List<AudioFile>)

data class UserPlaylists(
    val playlists: ArrayList<Playlist> = arrayListOf()
)


