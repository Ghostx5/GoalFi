package com.aspirefinance.goalfi.pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.aspirefinance.goalfi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.aspirefinance.goalfi.AuthViewModel


class homePage : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()// ✅ Correct ViewModel initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        val bottomnav = findViewById<BottomNavigationView>(R.id.navbar)

        bottomnav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    replaceFragment(Home())
                    true
                }
                R.id.announcements -> {
                    replaceFragment(Announcements())
                    true
                }
                R.id.budget -> {
                    replaceFragment(Budget())
                    true
                }
                R.id.learn -> {
                    replaceFragment(Learn())
                    true
                }
                R.id.settings -> {
                    replaceFragment(Settings())
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
