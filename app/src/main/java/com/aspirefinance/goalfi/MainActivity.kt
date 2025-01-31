package com.aspirefinance.goalfi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button  // Import Button class
import android.widget.Toast
import androidx.activity.viewModels
import com.aspirefinance.goalfi.pages.homePage
import com.aspirefinance.goalfi.pages.login
import com.aspirefinance.goalfi.pages.sign_up



class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_startpage)


        authViewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Authenticated -> {
                    val intent = Intent(this, homePage::class.java)
                    startActivity(intent)
                    finish() // Close MainActivity so users don't go back to it
                }
                is AuthState.Unauthenticated -> {
                    // Stay on start page (login/signup)
                    val loginBTN: Button = findViewById(R.id.login_redirect)
                    loginBTN.setOnClickListener{
                        val intent = Intent(this, login::class.java)  // Ensure the correct class name
                        startActivity(intent)
                    }
                    val signupBTN: Button = findViewById(R.id.signup_redirect)
                    signupBTN.setOnClickListener{
                        val intent = Intent(this, sign_up::class.java)  // Ensure the correct class name
                        startActivity(intent)
                    }
                }
                is AuthState.Loading -> {
                    // Optional: Show a loading indicator
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is AuthState.Error -> {
                    Toast.makeText(this, "Error + ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

}



