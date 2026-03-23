package com.practicum.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton = findViewById<MaterialButton>(R.id.search_button)
        searchButton.setOnClickListener {
            val goToSearchActivity = Intent(this, SearchActivity::class.java)
            startActivity(goToSearchActivity)
        }

        val mediaButton = findViewById<MaterialButton>(R.id.media_button)
        mediaButton.setOnClickListener {
            val goToMediaLibraryActivity = Intent(this, MediaLibraryActivity::class.java)
            startActivity(goToMediaLibraryActivity)
        }

        val settingsButton = findViewById<MaterialButton>(R.id.settings_button)
        settingsButton.setOnClickListener{
            val goToSettingsActivity = Intent(this, SettingsActivity::class.java)
            startActivity(goToSettingsActivity)

        }
    }
}