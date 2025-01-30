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

class sign_up : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()// ✅ Correct ViewModel initialization

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show() // ✅ Shows a Toast message
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)  // Set the layout for the sign-up activity


        val submit = findViewById<Button>(R.id.signupSubmit)


        submit.setOnClickListener {
            val email = findViewById<EditText>(R.id.userEmail)
            val password = findViewById<EditText>(R.id.userPassword)
            authViewModel.signup(email.getText().toString().trim(), password.getText().toString().trim())
            authViewModel.authState.observe(this) {state ->
                when(state) {
                    is AuthState.Authenticated -> {
                        Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, StartPage::class.java)
                        startActivity(intent)
                        finish()
                    }
                    is AuthState.Error -> {
                        // Show error message
                        Toast.makeText(this, "Something went wrong! ${state.message}", Toast.LENGTH_SHORT).show()
                    }
                    is AuthState.Loading -> {
                        // Handle loading state if needed
                        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    is AuthState.Unauthenticated -> {
                        // Handle unauthenticated state if needed
                        Toast.makeText(this, "Please log in to continue.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}