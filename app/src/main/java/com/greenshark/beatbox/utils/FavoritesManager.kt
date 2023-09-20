package com.greenshark.beatbox.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Carlos Jiménez on 16-Sep-23.
 */
class FavoritesManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        FAVORITES_PREFERENCES,
        Context.MODE_PRIVATE
    )

    fun getFavorites(): List<String> {
        val favoritesString = sharedPreferences.getStringSet(FAVORITES_KEY, setOf())
        return favoritesString?.toList() ?: emptyList()
    }

    fun addFavorite(trackId: String) {
        val favorites = getFavorites().toMutableSet()
        favorites.add(trackId)
        sharedPreferences.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

    fun removeFavorite(trackId: String) {
        val favorites = getFavorites().toMutableSet()
        favorites.remove(trackId)
        sharedPreferences.edit().putStringSet(FAVORITES_KEY, favorites).apply()
    }

    fun clearFavorites() {
        sharedPreferences.edit().remove(FAVORITES_KEY).apply()
    }
}

const val FAVORITES_KEY = "favorites"
const val FAVORITES_PREFERENCES = "MyFavorites"