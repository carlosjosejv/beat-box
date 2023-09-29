package com.greenshark.beatbox.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greenshark.beatbox.NavigatorViewModel
import com.greenshark.beatbox.R
import com.greenshark.beatbox.UPDATE_AUDIO_FILES
import com.greenshark.beatbox.adapters.AudioFileAdapter

class FilesFragment : Fragment() {

    companion object {
        fun newInstance() = FilesFragment()
    }

    private val viewModel: NavigatorViewModel by activityViewModels()

    private lateinit var audioFileAdapter: AudioFileAdapter
    private lateinit var audioFilesList: RecyclerView
    private lateinit var refreshButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_files, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        audioFileAdapter = AudioFileAdapter()

        audioFilesList = view.findViewById(R.id.audio_files_list)
        refreshButton = view.findViewById(R.id.refresh_button)

        val linearLayoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        audioFilesList.apply {
            layoutManager = linearLayoutManager
            adapter = audioFileAdapter
        }

        refreshButton.setOnClickListener {
            viewModel.selectItem(UPDATE_AUDIO_FILES)
        }

        viewModel.selectItem(UPDATE_AUDIO_FILES)

        viewModel.audioFiles.observe(viewLifecycleOwner) { list ->
            audioFileAdapter.apply {
                audioFiles = list
                notifyDataSetChanged()
            }
        }
    }

}