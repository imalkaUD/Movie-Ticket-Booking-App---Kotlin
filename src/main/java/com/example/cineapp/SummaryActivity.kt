package com.example.cineapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary)

        // Initialize views
        val moviePoster: ImageView = findViewById(R.id.summaryMoviePoster)
        val bookingDetails: TextView = findViewById(R.id.bookingDetails)
        val paymentDetails: TextView = findViewById(R.id.paymentDetails)
        val doneButton: Button = findViewById(R.id.doneButton)

        // Get data from intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "Movie"
        val date = intent.getStringExtra("MOVIE_DATE") ?: "Date"
        val time = intent.getStringExtra("MOVIE_TIME") ?: "Time"
        val seats = intent.getIntExtra("SEATS_COUNT", 1)
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 12)
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD") ?: "Credit Card"

        // Load movie poster (use same logic as BookingActivity)
        val imageResId = intent.getIntExtra("MOVIE_IMAGE_ID", 0)
        if (imageResId != 0) {
            moviePoster.setImageResource(getDrawableResourceId(imageResId))
        }

        // Set booking details
        bookingDetails.text = getString(R.string.booking_details, movieTitle, date, time, seats)
        paymentDetails.text = getString(R.string.payment_details, totalPrice, paymentMethod)

        doneButton.setOnClickListener {
            // Navigate back to home screen
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }

    private fun getDrawableResourceId(imageViewId: Int): Int {
        return when (imageViewId) {
            R.id.imageView18 -> R.drawable.im
            R.id.imageView20 -> R.drawable.sn
            R.id.imageView19 -> R.drawable.image
            R.id.imageView21 -> R.drawable.ii
            R.id.imageView22 -> R.drawable.ccc
            R.id.imageView23 -> R.drawable.bb
            R.id.imageView26 -> R.drawable.kk
            R.id.imageView25 -> R.drawable.ff
            R.id.imageView24 -> R.drawable.ab
            else -> R.drawable.placeholder
        }
    }
}