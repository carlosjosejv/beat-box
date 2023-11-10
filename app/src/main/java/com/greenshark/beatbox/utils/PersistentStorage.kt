package com.greenshark.beatbox.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
import android.support.v4.media.MediaDescriptionCompat
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersistentStorage private constructor(val context: Context) {

    /**
     * Store any data which must persist between restarts, such as the most recently played song.
     */
    private var preferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    companion object {

        @Volatile
        private var instance: PersistentStorage? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: PersistentStorage(context).also { instance = it }
            }
    }

    fun setBooleanPreference(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBooleanPreference(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setStringPreference(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getStringPreference(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue)!!
    }

    fun setIntPreference(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun getIntPreference(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun setLongPreference(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getLongPreference(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    fun removeKey(key: String?) {
        val editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }
}

private const val PREFERENCES_NAME = "beat_box_preferences"
const val PREFERENCES_KEY = "preferences"
