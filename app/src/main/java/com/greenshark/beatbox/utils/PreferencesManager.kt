package com.greenshark.beatbox.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.greenshark.beatbox.models.AudioFile
import com.greenshark.beatbox.models.UserPreferences

/**
 * Created by Carlos Jiménez on 16-Sep-23.
 */
class PreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserPreferences(userPreferences: UserPreferences) {
        val json = gson.toJson(userPreferences)
        sharedPreferences.edit().putString("user_preferences", json).apply()
    }

    fun getUserPreferences(): UserPreferences {
        val json = sharedPreferences.getString("user_preferences", null)
        return if (json != null) {
            gson.fromJson(json, UserPreferences::class.java)
        } else {
            UserPreferences()
        }
    }

}

const val FAVORITES_KEY = "favorites"
const val USER_PREFERENCES = "user_preferences"