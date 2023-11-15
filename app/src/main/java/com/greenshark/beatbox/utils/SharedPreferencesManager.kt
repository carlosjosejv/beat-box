package com.greenshark.beatbox.utils

import android.content.Context
import com.google.gson.Gson
import com.greenshark.beatbox.models.UserFavorites

/**
 * Created by Carlos Jiménez on 15-Nov-23.
 */
class SharedPreferencesManager(private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(USER_PREFERENCES_KEY, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserFavorites(userFavorites: UserFavorites) {
        val json = gson.toJson(userFavorites)
        sharedPreferences.edit().putString(USER_PREFERENCES_KEY, json).apply()
    }

    fun getUserFavorites(): UserFavorites {
        val json = sharedPreferences.getString(USER_PREFERENCES_KEY, null)
        return if (json != null) {
            gson.fromJson(json, UserFavorites::class.java)
        } else {
            UserFavorites()
        }
    }
}

private const val USER_PREFERENCES_KEY = "user_preferences_key"