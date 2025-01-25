package com.aspirefinance.goalfi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.View
import android.widget.Button  // Import Button class


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

}



