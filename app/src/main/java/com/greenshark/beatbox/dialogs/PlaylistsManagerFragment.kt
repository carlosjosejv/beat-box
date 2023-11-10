package com.greenshark.beatbox.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.greenshark.beatbox.NavigatorViewModel

/**
 * Created by Carlos Jiménez on 10-Nov-23.
 */
class PlaylistsManagerFragment: BottomSheetDialogFragment() {

    private val viewModel: NavigatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}