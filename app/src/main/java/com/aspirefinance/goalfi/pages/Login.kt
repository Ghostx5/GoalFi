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

class login : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()// ✅ Correct ViewModel initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)  // Set the layout for the sign-up activity

        supportActionBar?.title = ""

        super.onResume()
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }


        val submitLogin = findViewById<Button>(R.id.loginSubmit)


        submitLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailField)
            val password = findViewById<EditText>(R.id.passwordField)
            authViewModel.login(email.getText().toString().trim(), password.getText().toString().trim())
            authViewModel.authState.observe(this) {state ->
                when(state) {
                    is AuthState.Authenticated -> {
                        val intent = Intent(this, homePage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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