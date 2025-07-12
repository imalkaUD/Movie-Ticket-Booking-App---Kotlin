package com.example.cineapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish() // Return to HomeActivity
        }
    }
}