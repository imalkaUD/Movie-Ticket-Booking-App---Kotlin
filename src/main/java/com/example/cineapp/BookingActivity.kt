package com.example.cineapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {

    // Declare constants for pricing
    companion object {
        const val TICKET_PRICE = 12 // $12 per ticket
        const val EXTRA_MOVIE_TITLE = "MOVIE_TITLE"
        const val EXTRA_MOVIE_DATE = "MOVIE_DATE"
        const val EXTRA_MOVIE_TIME = "MOVIE_TIME"
        const val EXTRA_SEATS_COUNT = "SEATS_COUNT"
        const val EXTRA_TOTAL_PRICE = "TOTAL_PRICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        // Initialize views
        val movieTitle: TextView = findViewById(R.id.movieTitle)
        val moviePoster: ImageView = findViewById(R.id.moviePoster)
        val datePicker: DatePicker = findViewById(R.id.datePicker)
        val seatPicker: NumberPicker = findViewById(R.id.seatPicker)
        val bookButton: Button = findViewById(R.id.bookButton)
        val timeRadioGroup: RadioGroup = findViewById(R.id.timeRadioGroup)

        // Set movie details from intent
        intent.extras?.let {
            movieTitle.text = it.getString(EXTRA_MOVIE_TITLE, "Movie")
            val imageResId = it.getInt("MOVIE_IMAGE_ID", 0)
            if (imageResId != 0) {
                moviePoster.setImageResource(getDrawableResourceId(imageResId))
            }
        }

        // Configure seat picker
        with(seatPicker) {
            minValue = 1
            maxValue = 10
            wrapSelectorWheel = false
            value = 2 // Default to 2 seats
        }

        // Set date picker constraints
        with(datePicker) {
            val calendar = Calendar.getInstance()
            minDate = calendar.timeInMillis
            calendar.add(Calendar.MONTH, 3) // Allow booking up to 3 months in advance
            maxDate = calendar.timeInMillis
        }

        // Set default time selection
        timeRadioGroup.check(R.id.eveningRadio)

        bookButton.setOnClickListener {
            if (validateBooking(timeRadioGroup)) {
                navigateToPaymentScreen(
                    movieTitle = movieTitle.text.toString(),
                    date = getSelectedDate(datePicker),
                    time = getSelectedTime(timeRadioGroup),
                    seats = seatPicker.value
                )
            }
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

    private fun validateBooking(timeRadioGroup: RadioGroup): Boolean {
        if (timeRadioGroup.checkedRadioButtonId == -1) {
            showToast("Please select a show time")
            return false
        }
        return true
    }

    private fun getSelectedDate(datePicker: DatePicker): String {
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).run {
            val calendar = Calendar.getInstance().apply {
                set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            }
            format(calendar.time)
        }
    }

    private fun getSelectedTime(timeRadioGroup: RadioGroup): String {
        return when (timeRadioGroup.checkedRadioButtonId) {
            R.id.morningRadio -> "10:00 AM"
            R.id.afternoonRadio -> "2:00 PM"
            R.id.eveningRadio -> "6:00 PM"
            else -> ""
        }
    }

    private fun navigateToPaymentScreen(
        movieTitle: String,
        date: String,
        time: String,
        seats: Int
    ) {
        Intent(this, PaymentActivity::class.java).apply {
            putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            putExtra(EXTRA_MOVIE_DATE, date)
            putExtra(EXTRA_MOVIE_TIME, time)
            putExtra(EXTRA_SEATS_COUNT, seats)
            putExtra(EXTRA_TOTAL_PRICE, seats * TICKET_PRICE)
            startActivity(this)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}