package com.aspirefinance.goalfi.pages

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aspirefinance.goalfi.AuthState
import com.aspirefinance.goalfi.AuthViewModel
import com.aspirefinance.goalfi.R
import androidx.activity.viewModels  // Required for 'by viewModels()' delegate




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
            val email = findViewById<EditText>(R.id.emailBox)
            val password = findViewById<EditText>(R.id.passwordBox)
            authViewModel.signup(email.getText().toString().trim(), password.getText().toString().trim())
        }

        authViewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Authenticated -> showMessage("Signup successful!")
                is AuthState.Error -> showMessage(state.message)
                else -> {} // No action for Unauthenticated/Loading
            }
        }

    }

}