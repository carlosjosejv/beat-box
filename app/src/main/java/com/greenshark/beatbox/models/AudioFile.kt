package com.greenshark.beatbox.models

import android.net.Uri

/**
 * Created by Carlos Jiménez on 05-Sep-23.
 */
data class AudioFile(
    val uri: Uri,
    val name: String,
    val duration: Int,
    val size: Int
)