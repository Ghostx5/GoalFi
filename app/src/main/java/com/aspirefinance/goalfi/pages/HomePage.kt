package com.aspirefinance.goalfi.pages

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.aspirefinance.goalfi.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aspirefinance.goalfi.AuthViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.aspirefinance.goalfi.User


class homePage : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()// ✅ Correct ViewModel initialization

    private lateinit var bottomnav: BottomNavigationView
    private lateinit var fullNameTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        bottomnav = findViewById(R.id.navbar) // Initialize here

        bottomnav.setOnItemSelectedListener { item ->
            when (item.itemId) {
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

