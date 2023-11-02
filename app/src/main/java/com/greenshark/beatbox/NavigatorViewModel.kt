package com.greenshark.beatbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greenshark.beatbox.models.AudioFile

/**
 * Created by Carlos Jiménez on 15-Sep-23.
 */
class NavigatorViewModel : ViewModel() {

    private val mutableAudioFiles = MutableLiveData<List<AudioFile>>()
    val audioFiles: LiveData<List<AudioFile>> get() = mutableAudioFiles

    private val mutableSelectedItem = MutableLiveData<Int>()
    val selectedItem: LiveData<Int> get() = mutableSelectedItem

    private val mutableFavorites = MutableLiveData<List<AudioFile>>()
    val favorites: LiveData<List<AudioFile>> get() = mutableFavorites

    fun selectItem(item: Int) {
        mutableSelectedItem.value = item
    }

    fun fetchList(list: List<AudioFile>) {
        mutableAudioFiles.value = list
    }

    fun setUserFavorites(favorites: List<AudioFile>) {
        mutableFavorites.value = favorites
    }

    fun setFavorite(audioFile: AudioFile): Boolean {
        val currentFavorites = favorites.value ?: mutableSetOf()
        val newFavorites = currentFavorites.toMutableList()

        val favorite = isFavorite(audioFile)

        if (favorite) {
            newFavorites.remove(audioFile)
        } else {
            newFavorites.add(audioFile)
        }

        mutableFavorites.value = newFavorites

        return !favorite
    }

    fun isFavorite(audioFile: AudioFile): Boolean {
        val currentFavorites = favorites.value ?: mutableSetOf()
        return currentFavorites.contains(audioFile)
    }
}