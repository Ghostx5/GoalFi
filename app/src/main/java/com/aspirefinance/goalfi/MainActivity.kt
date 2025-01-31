package com.aspirefinance.goalfi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button  // Import Button class
import androidx.activity.viewModels
import com.aspirefinance.goalfi.pages.login
import com.aspirefinance.goalfi.pages.sign_up



class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_startpage)


        authViewModel.checkAuthStatus()

        super.onResume()
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }

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



