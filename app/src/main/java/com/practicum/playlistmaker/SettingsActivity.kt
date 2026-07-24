package com.practicum.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val shareButton = findViewById<TextView>(R.id.share_button)
        shareButton.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app))

            startActivity(shareIntent)
        }

        val supportButton = findViewById<TextView>(R.id.support_button)
        supportButton.setOnClickListener {
            val createMailIntent = Intent().apply {
                data = Uri.parse("mailto:")
                action = Intent.ACTION_SENDTO
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))
            }

            startActivity(createMailIntent)
        }

        val termsOfUseButton = findViewById<TextView>(R.id.terms_of_use_button)
        termsOfUseButton.setOnClickListener {
            val openTermsOfUseIntent = Intent( Intent.ACTION_VIEW, getString(R.string.terms_of_use).toUri())
            startActivity(openTermsOfUseIntent)
        }
    }
}