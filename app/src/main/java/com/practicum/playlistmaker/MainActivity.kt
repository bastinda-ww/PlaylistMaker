package com.practicum.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import com.google.android.gms.maps3d.model.ImageView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val search_button = findViewById<MaterialButton>(R.id.search_button)
        search_button.setOnClickListener {
            val goToSearchActivity = Intent(this, SearchActivity::class.java)
            startActivity(goToSearchActivity)
        }

        val media_button = findViewById<MaterialButton>(R.id.media_button)
        media_button.setOnClickListener {
            val goToMediaLibraryActivity = Intent(this, MediaLibraryActivity::class.java)
            startActivity(goToMediaLibraryActivity)
        }

        val settings_button = findViewById<MaterialButton>(R.id.settings_button)
        settings_button.setOnClickListener{
            val goToSettingsActivity = Intent(this, SettingsActivity::class.java)
            startActivity(goToSettingsActivity)

        }
    }
}