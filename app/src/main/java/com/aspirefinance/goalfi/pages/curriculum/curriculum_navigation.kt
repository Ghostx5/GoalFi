package com.aspirefinance.goalfi.pages.curriculum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aspirefinance.goalfi.R
import com.aspirefinance.goalfi.pages.homePage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [curriculum_navigation.newInstance] factory method to
 * create an instance of this fragment.
 */
class curriculum_navigation : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curriculum_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val module_1 = view.findViewById<Button>(R.id.module_1)
        val module_2 = view.findViewById<Button>(R.id.module_2)
        val module_3 = view.findViewById<Button>(R.id.module_3)
        val module_4 = view.findViewById<Button>(R.id.module_4)
        val module_5 = view.findViewById<Button>(R.id.module_5)
        val module_6 = view.findViewById<Button>(R.id.module_6)
        val module_7 = view.findViewById<Button>(R.id.module_7)
        val module_8 = view.findViewById<Button>(R.id.module_8)
        val module_9 = view.findViewById<Button>(R.id.module_9)

        module_1.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_1())
        }
        module_2.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_2())
        }
        module_3.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_3())
        }
        module_4.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_4())
        }
        module_5.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_5())
        }
        module_6.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_6())
        }
        module_7.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_7())
        }
        module_8.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_8())
        }
        module_9.setOnClickListener {
            (activity as? homePage)?.replaceFragment(module_9())
        }
    }
}
