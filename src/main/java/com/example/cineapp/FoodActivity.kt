package com.example.cineapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food)

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish() // Return to HomeActivity
        }
    }
}