package com.aspirefinance.goalfi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aspirefinance.goalfi.R
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            (it as AppCompatActivity).supportActionBar?.title = "Home"
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private lateinit var fullNameTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        fullNameTextView = view.findViewById(R.id.loginwlcmsg)

        // Retrieve the user's full name
        val activity = activity as? homePage
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email


        // Check if a user is logged in
        if (user != null) {
            // Get the user's email, format it to get the userId (remove @ and .)
            val userId = user.email?.replace("[@.]".toRegex(), "") ?: ""

            // Call the method from the Activity to retrieve the user's full name

            activity?.retrieveUserFullName(userId) { fullName ->
                fullNameTextView.text = fullName // Update fragment's TextView
            }
        } else {
            // Handle the case where the user is not logged in
            fullNameTextView.text = "User not logged in"
        }
    }
}



