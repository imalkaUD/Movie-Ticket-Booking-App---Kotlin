package com.example.cineapp



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cineapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button by its ID
        val button: Button = findViewById(R.id.button)

        // Set an OnClickListener on the button
        button.setOnClickListener {
            // Create an Intent to start the SignupActivity
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}