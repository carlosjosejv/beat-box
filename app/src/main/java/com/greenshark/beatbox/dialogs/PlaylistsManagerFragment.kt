package com.greenshark.beatbox.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.greenshark.beatbox.NavigatorViewModel
import com.greenshark.beatbox.R

/**
 * Created by Carlos Jiménez on 10-Nov-23.
 */
class PlaylistsManagerFragment : DialogFragment(), OnClickListener {

    private val viewModel: NavigatorViewModel by activityViewModels()

    private lateinit var back: ImageView
    private lateinit var newPlaylist: Button
    private lateinit var searchView: SearchView
    private lateinit var orderButton: Button
    private lateinit var playlistsList: RecyclerView
    private lateinit var okButton: Button

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BeatBox_DialogFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlists_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.back)
        back.setOnClickListener(this)
        newPlaylist = view.findViewById(R.id.new_playlist_button)
        searchView = view.findViewById(R.id.searchView)
        orderButton = view.findViewById(R.id.order_button)
        playlistsList = view.findViewById(R.id.playlists_list)
        okButton = view.findViewById(R.id.ok_button)
        okButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            back,
            okButton -> {
                dismiss()
            }
        }
    }
}