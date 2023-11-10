package com.greenshark.beatbox.utils

import android.content.Context
import com.google.gson.Gson
import com.greenshark.beatbox.models.UserPreferences

/**
 * Created by Carlos Jiménez on 03-Nov-23.
 */
object UserPropertiesUtil {

    fun saveFavorites(context: Context, downloadedFiles: UserPreferences) {
        try {
            PersistentStorage.getInstance(context)
                .setStringPreference(USER_PREFERENCES_KEY, Gson().toJson(downloadedFiles))
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        }
    }

    fun getFavorites(context: Context): UserPreferences {
        val savedRadioRecordings = PersistentStorage.getInstance(context).getStringPreference(
            USER_PREFERENCES_KEY, ""
        )
        if (!savedRadioRecordings.isNullOrEmpty()) {
            return Gson().fromJson(savedRadioRecordings, UserPreferences::class.java)
        }
        return UserPreferences()
    }
}

const val USER_PREFERENCES_KEY = "user_preferences_key"