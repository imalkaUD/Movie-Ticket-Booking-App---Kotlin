package com.example.cineapp




import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize views
        emailEditText = findViewById(R.id.editTextTextEmailAddress2)
        passwordEditText = findViewById(R.id.editTextTextPassword2)
        loginButton = findViewById(R.id.button5)

        // Set click listener for login button
        loginButton.setOnClickListener {
            if (validateForm()) {
                // Form is valid, proceed to home
                navigateToHome()
            }
        }
    }

    private fun validateForm(): Boolean {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        return when {
            email.isEmpty() -> {
                emailEditText.error = "Email is required"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEditText.error = "Enter a valid email"
                false
            }
            password.isEmpty() -> {
                passwordEditText.error = "Password is required"
                false
            }
            password.length < 6 -> {
                passwordEditText.error = "Password must be at least 6 characters"
                false
            }
            else -> true
        }
    }

    private fun navigateToHome() {
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close the login activity
    }
}