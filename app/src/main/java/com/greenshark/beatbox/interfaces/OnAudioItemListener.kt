package com.greenshark.beatbox.interfaces

import com.greenshark.beatbox.models.AudioFile

/**
 * Created by Carlos Jiménez on 03-Nov-23.
 */
interface OnAudioItemListener {
    fun onClick(audioFile: AudioFile)
}