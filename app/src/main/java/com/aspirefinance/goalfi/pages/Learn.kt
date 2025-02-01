package com.aspirefinance.goalfi.pages

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aspirefinance.goalfi.MainActivity
import com.aspirefinance.goalfi.R
import com.aspirefinance.goalfi.pages.curriculum.curriculum_navigation



/**
 * A simple [Fragment] subclass.
 * Use the [Learn.newInstance] factory method to
 * create an instance of this fragment.
 */
class Learn : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            (it as AppCompatActivity).supportActionBar?.title = "Learn"
        }
        return inflater.inflate(R.layout.fragment_learn, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        val curriculum_redirect = view.findViewById<Button>(R.id.curriculum_redirect)
        val chatbot_redirect = view.findViewById<Button>(R.id.chatbot_redirect)

        curriculum_redirect.setOnClickListener {
            (activity as? homePage)?.replaceFragment(curriculum_navigation())
        }
    }
}
