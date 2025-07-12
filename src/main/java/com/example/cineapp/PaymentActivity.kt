package com.example.cineapp



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)

        // Initialize views
        val movieInfo: TextView = findViewById(R.id.movieInfo)
        val priceInfo: TextView = findViewById(R.id.priceInfo)
        val confirmPaymentButton: Button = findViewById(R.id.confirmPaymentButton)
        val paymentMethodGroup: RadioGroup = findViewById(R.id.paymentMethodGroup)

        // Get booking details from intent
        val movie = intent.getStringExtra("MOVIE_TITLE") ?: "Movie"
        val date = intent.getStringExtra("MOVIE_DATE") ?: "Date"
        val time = intent.getStringExtra("MOVIE_TIME") ?: "Time"
        val seats = intent.getIntExtra("SEATS_COUNT", 1)
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 10)

        // Set booking info
        movieInfo.text = "$movie\n$date at $time\n$seats seat(s)"
        priceInfo.text = "Total: \$$totalPrice"

        // Set default payment method
        paymentMethodGroup.check(R.id.creditCardRadio)

        confirmPaymentButton.setOnClickListener {
            if (paymentMethodGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val paymentMethod = when (paymentMethodGroup.checkedRadioButtonId) {
                R.id.creditCardRadio -> "Credit Card"
                R.id.debitCardRadio -> "Debit Card"
                R.id.paypalRadio -> "Lanka Pay"
                else -> "Unknown"
            }

            showPaymentConfirmation(movie, date, time, seats, totalPrice, paymentMethod)
        }
    }

    // Replace the showPaymentConfirmation function in PaymentActivity with:
    private fun showPaymentConfirmation(
        movie: String,
        date: String,
        time: String,
        seats: Int,
        totalPrice: Int,
        paymentMethod: String
    ) {
        val intent = Intent(this, SummaryActivity::class.java).apply {
            putExtra("MOVIE_TITLE", movie)
            putExtra("MOVIE_DATE", date)
            putExtra("MOVIE_TIME", time)
            putExtra("SEATS_COUNT", seats)
            putExtra("TOTAL_PRICE", totalPrice)
            putExtra("PAYMENT_METHOD", paymentMethod)
            putExtra("MOVIE_IMAGE_ID", intent.getIntExtra("MOVIE_IMAGE_ID", 0))
        }
        startActivity(intent)
        finish()
    }
}