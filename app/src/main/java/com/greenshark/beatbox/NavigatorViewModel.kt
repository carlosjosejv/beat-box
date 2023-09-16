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

    fun selectItem(item: Int) {
        mutableSelectedItem.value = item
    }

    fun fetchList(list: List<AudioFile>) {
        mutableAudioFiles.value = list
    }
}