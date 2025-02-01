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
import com.google.firebase.auth.FirebaseAuth


class homePage : AppCompatActivity() {

    private lateinit var bottomnav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        setContentView(R.layout.activity_home_page)

        supportActionBar?.title = ""


        bottomnav = findViewById(R.id.navbar) // Initialize here
        replaceFragment(Home())

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

    internal fun retrieveUserFullName(userId: String,  callback: (String) -> Unit) {
        // Get a reference to the specific user node in Firebase
        val database = FirebaseDatabase.getInstance().getReference("users").child(userId)

        // Listen for changes in the specific user's data
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(User::class.java)
                    val fullName = user?.name ?: "User not found"
                        // Update the TextView with the user's full name
                        callback(fullName)
                } else {
                    Toast.makeText(this@homePage, "User not found", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Toast.makeText(this@homePage, "Failed to load user data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}

