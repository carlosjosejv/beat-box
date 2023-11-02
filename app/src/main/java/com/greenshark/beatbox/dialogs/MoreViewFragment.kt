package com.greenshark.beatbox.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.greenshark.beatbox.NavigatorViewModel
import com.greenshark.beatbox.R
import com.greenshark.beatbox.models.AudioFile

/**
 * Created by Carlos Jiménez on 15-Sep-23.
 */
class MoreViewFragment(private val audioFile: AudioFile) : BottomSheetDialogFragment() {

    private val viewModel: NavigatorViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val audioName: TextView = view.findViewById(R.id.audio_name)
        val addPlaylist: ConstraintLayout = view.findViewById(R.id.add_playlist)
        val favorite: ConstraintLayout = view.findViewById(R.id.favorite)
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon)

        audioName.text = audioFile.name

        addPlaylist.setOnClickListener {

        }

        if (viewModel.isFavorite(audioFile)) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_fill)
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite)
        }

        favorite.setOnClickListener {
            favoriteIcon.setImageResource(
                if (viewModel.setFavorite(audioFile))
                    R.drawable.ic_favorite_fill
                else
                    R.drawable.ic_favorite)
        }
    }
}