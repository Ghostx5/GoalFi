package com.aspirefinance.goalfi.pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aspirefinance.goalfi.AuthState
import com.aspirefinance.goalfi.AuthViewModel
import com.aspirefinance.goalfi.R
import androidx.activity.viewModels
import com.aspirefinance.goalfi.Finance
import com.aspirefinance.goalfi.MainActivity
import com.aspirefinance.goalfi.User
import com.google.firebase.database.FirebaseDatabase

class sign_up : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()// ✅ Correct ViewModel initialization

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show() // ✅ Shows a Toast message
    }

    private fun createAccount(fullNameEditText: String, zipCodeEditText: String, userEmail: String) {
        val database = FirebaseDatabase.getInstance().getReference("users")

            // Get user input
            val fullName = fullNameEditText
            val zipCode = zipCodeEditText

            // Ensure input is valid
            if (fullName.isNotEmpty() && zipCode.isNotEmpty()) {
                val userId =  userEmail.replace("[@.]".toRegex(), "")// Automatically generate a unique ID for each user
                val finance = Finance(null, zipCode)
                val user = User(finance, fullName)

                // Push data to Firebase
                if (userId != null) {
                    database.child(userId).setValue(user)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "User registered successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_up)  // Set the layout for the sign-up activity

            supportActionBar?.title = ""


            super.onResume()
            if (intent.getBooleanExtra("EXIT", false)) {
                finish()
            }


            val submitSignUp = findViewById<Button>(R.id.signupSubmit)


            submitSignUp.setOnClickListener {
                val email = findViewById<EditText>(R.id.userEmail).getText().toString().trim()
                val password = findViewById<EditText>(R.id.userPassword).getText().toString().trim()
                val userFullName = findViewById<EditText>(R.id.userFullName).getText().toString().trim()
                val userZipcode = findViewById<EditText>(R.id.userZipcode).getText().toString().trim()
                authViewModel.signup(email, password)
                authViewModel.authState.observe(this) { state ->
                    when (state) {
                        is AuthState.Authenticated -> {
                            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                            createAccount(userFullName, userZipcode, email)
                            val intent = Intent(this, homePage::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        }

                        is AuthState.Error -> {
                            // Show error message
                            Toast.makeText(
                                this,
                                "Something went wrong! ${state.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is AuthState.Loading -> {
                            // Handle loading state if needed
                            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                        }

                        is AuthState.Unauthenticated -> {
                            // Handle unauthenticated state if needed
                            Toast.makeText(this, "Please log in to continue.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
