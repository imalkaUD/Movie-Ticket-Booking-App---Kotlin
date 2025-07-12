package com.example.cineapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    // Movie data mapping
    private val movieData = mapOf(
        R.id.imageView18 to Movie("Nelum Kuluna", R.drawable.im, false),
        R.id.imageView20 to Movie("SnowWhite", R.drawable.sn, false),
        R.id.imageView19 to Movie("Rani", R.drawable.image, false),
        R.id.imageView21 to Movie("Captain America BNW", R.drawable.ii, false),
        R.id.imageView22 to Movie("MOANA 2", R.drawable.ccc, false),
        R.id.imageView23 to Movie("MUFASA", R.drawable.bb, false),
        R.id.imageView26 to Movie("Coming Soon", R.drawable.kk, true),
        R.id.imageView25 to Movie("Coming Soon", R.drawable.ff, true),
        R.id.imageView24 to Movie("Coming Soon", R.drawable.ab, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        // Set click listeners for movie posters
        movieData.keys.forEach { imageViewId ->
            findViewById<ImageView>(imageViewId).setOnClickListener {
                if (!movieData[imageViewId]?.isComingSoon!! == true) {
                    navigateToBooking(imageViewId)
                } else {
                    showComingSoonToast()
                }
            }
        }

        // Set click listener for profile icon
        findViewById<ImageView>(R.id.imageView27).setOnClickListener {
            navigateToProfile()
        }

        // Set click listener for food icon
        findViewById<ImageView>(R.id.imageView28).setOnClickListener {
            navigateToFood()
        }
    }

    private fun navigateToBooking(imageViewId: Int) {
        movieData[imageViewId]?.let { movie ->
            Intent(this, BookingActivity::class.java).apply {
                putExtra("MOVIE_TITLE", movie.title)
                putExtra("MOVIE_IMAGE_ID", imageViewId)
                startActivity(this)
            }
        }
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun navigateToFood() {
        startActivity(Intent(this, FoodActivity::class.java))
    }

    private fun showComingSoonToast() {
        Toast.makeText(this, "This movie is coming soon!", Toast.LENGTH_SHORT).show()
    }

    // Data class for movie information
    private data class Movie(
        val title: String,
        val imageResId: Int,
        val isComingSoon: Boolean
    )
}