package com.example.capsuleapp.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capsuleapp.R
import com.google.android.material.card.MaterialCardView

class QuizResultFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textView: TextView
    private lateinit var homePage : MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_result, container, false)
        textView = view.findViewById(R.id.textView)
        sharedPreferences = activity?.getSharedPreferences("sharedPrefs", 0)!!
        homePage = view.findViewById(R.id.homePage)

        val totalPoints = arguments?.getInt("totalPoints")

        if(arguments==null){
            textView.text = 0.toString()

        }else{
        textView.text = totalPoints.toString()}

        homePage.setOnClickListener {
            val fragment = HomeFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        return view
    }


}