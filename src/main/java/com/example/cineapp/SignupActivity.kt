package com.example.cineapp




import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        // Initialize views
        nameEditText = findViewById(R.id.editTextText)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        signupButton = findViewById(R.id.button2)

        // Set click listener for signup button
        signupButton.setOnClickListener {
            if (validateForm()) {
                // Form is valid, proceed to login
                navigateToLogin()
            }
        }

        // Optional: Set click listener for "Sign In" text
        findViewById<TextView>(R.id.textView4).setOnClickListener {
            navigateToLogin()
        }
    }

    private fun validateForm(): Boolean {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        return when {
            name.isEmpty() -> {
                nameEditText.error = "Name is required"
                false
            }
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

    private fun navigateToLogin() {
        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close the signup activity
    }
}