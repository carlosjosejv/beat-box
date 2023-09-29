package com.greenshark.beatbox.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.greenshark.beatbox.fragments.FavoritesFragment
import com.greenshark.beatbox.fragments.FilesFragment
import com.greenshark.beatbox.fragments.PlaylistsFragment

/**
 * Created by Carlos Jiménez on 15-Sep-23.
 */
class NavigatorAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PlaylistsFragment.newInstance()
            }

            1 -> {
                FavoritesFragment.newInstance()
            }

            2 -> {
                FilesFragment.newInstance()
            }

            else -> {
                throw IllegalArgumentException("Invalid position")
            }
        }
    }
}

