package com.example.capsuleapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capsuleapp.R

class MainActivity : AppCompatActivity() {

    private var fragmentContainer : androidx.fragment.app.FragmentContainerView? = null
    private var fragmentManager : androidx.fragment.app.FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container)
        fragmentManager = supportFragmentManager

        val homeFragment = com.example.capsuleapp.fragments.HomeFragment()
        fragmentManager!!.beginTransaction().add(R.id.fragment_container, homeFragment).commit()


    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (fragmentManager!!.backStackEntryCount > 0) {
            fragmentManager!!.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}