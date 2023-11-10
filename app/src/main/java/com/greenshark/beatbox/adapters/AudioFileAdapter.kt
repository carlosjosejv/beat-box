package com.greenshark.beatbox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.greenshark.beatbox.R
import com.greenshark.beatbox.dialogs.MoreViewFragment
import com.greenshark.beatbox.interfaces.OnAudioItemListener
import com.greenshark.beatbox.models.AudioFile

/**
 * Created by Carlos Jiménez on 15-Sep-23.
 */
class AudioFileAdapter(val onAudioItemListener: OnAudioItemListener) : RecyclerView.Adapter<AudioFileAdapter.ViewHolder>() {

    var audioFiles: List<AudioFile> = emptyList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val audioItem: ConstraintLayout = view.findViewById(R.id.audio_item)
        val audioName: TextView = view.findViewById(R.id.audio_name)
        val moreButton: ImageButton = view.findViewById(R.id.more_button)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audio_file, parent, false)

        return ViewHolder(view)
    }

    // Return the size of your audioFiles (invoked by the layout manager)
    override fun getItemCount(): Int {
        return audioFiles.size
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.audioItem.setOnClickListener {
            onAudioItemListener.onClick(audioFiles[position])
        }
        holder.audioName.text = audioFiles[position].name
        holder.moreButton.setOnClickListener {
            val fragmentManager =
                (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val moreViewFragment = MoreViewFragment(audioFiles[position])
            moreViewFragment.show(fragmentManager, moreViewFragment.tag)
        }
    }
}