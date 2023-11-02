package com.greenshark.beatbox.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greenshark.beatbox.models.AudioFile

/**
 * Created by Carlos Jiménez on 16-Sep-23.
 */
class PreferencesManager {

    fun saveFavoritesInSharedPreferences(context: Context, list: List<AudioFile>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(FAVORITES_KEY, json)
        editor.apply()
    }

    fun getFavoritesFromSharedPreferences(context: Context): List<AudioFile> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(FAVORITES_KEY, null)
        val gson = Gson()
        val type = object : TypeToken<List<AudioFile>>() {}.type

        return gson.fromJson(json, type) ?: emptyList()
    }

}

const val FAVORITES_KEY = "favorites"
const val USER_PREFERENCES = "user_preferences"