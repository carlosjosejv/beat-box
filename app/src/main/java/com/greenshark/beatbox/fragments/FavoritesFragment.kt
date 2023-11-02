package com.greenshark.beatbox.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenshark.beatbox.NavigatorViewModel
import com.greenshark.beatbox.R
import com.greenshark.beatbox.adapters.AudioFileAdapter
import com.greenshark.beatbox.utils.PreferencesManager

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private val viewModel: NavigatorViewModel by activityViewModels()

    private lateinit var audioFileAdapter: AudioFileAdapter
    private lateinit var audioFilesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        audioFileAdapter = AudioFileAdapter()

        audioFilesList = view.findViewById(R.id.audio_files_list)

        val linearLayoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        audioFilesList.apply {
            layoutManager = linearLayoutManager
            adapter = audioFileAdapter
        }

        viewModel.favorites.observe(viewLifecycleOwner) { list ->
            audioFileAdapter.apply {
                audioFiles = list
                notifyDataSetChanged()
            }
        }
    }

}