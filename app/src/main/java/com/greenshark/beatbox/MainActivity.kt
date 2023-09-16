package com.greenshark.beatbox

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.greenshark.beatbox.adapters.NavigatorAdapter
import com.greenshark.beatbox.utils.AudioScanner

class MainActivity : AppCompatActivity() {

    private lateinit var audioScanner: AudioScanner

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    // Using the viewModels() Kotlin property delegate from the activity-ktx
    // artifact to retrieve the ViewModel in the activity scope.
    /*
        implementation("androidx.fragment:fragment-ktx:1.6.1")
     */
    private val viewModel: NavigatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioScanner = AudioScanner(this.contentResolver)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = NavigatorAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Playlists"
                1 -> tab.text = "Favorites"
                2 -> tab.text = "Folders"
            }
        }.attach()

        viewModel.selectedItem.observe(this) { item ->
            when (item) {
                UPDATE_AUDIO_FILES -> {
                    checkReadMediaAudioPermission()
                }
            }
        }
    }

    private fun checkReadMediaAudioPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            scanAudio()
        } else {
            showPermissionRationale()
        }
    }

    private fun showPermissionRationale() {
        AlertDialog.Builder(this)
            .setTitle("Read Media Audio Permission Needed")
            .setMessage("This app requires access to the audio files.")
            .setPositiveButton("OK") { dialog, _ ->
                // Request the permission when the user clicks "OK"
                requestReadMediaAudioPermission()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // User cancelled the permission request
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun requestReadMediaAudioPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_MEDIA_AUDIO),
                READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val isPermissionGranted =
            grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

        when (requestCode) {
            READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE -> {
                if (isPermissionGranted) {
                    // Permission is granted. Continue the action or workflow in your app.
                    scanAudio()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the feature requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
            }

            READ_MEDIA_IMAGES_PERMISSION_REQUEST_CODE -> {
                if (isPermissionGranted) {
                    // Permission is granted. Continue the action or workflow in your app.
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the feature requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
            }

            READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE -> {
                if (isPermissionGranted) {
                    // Permission is granted. Continue the action or workflow in your app.
                    scanAudio()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the feature requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
            }

            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun scanAudio() {
        viewModel.fetchList(audioScanner.scanAudio())
    }
}

const val READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE = 2001
const val READ_MEDIA_IMAGES_PERMISSION_REQUEST_CODE = 2002
const val READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE = 2003

const val UPDATE_AUDIO_FILES = 3001