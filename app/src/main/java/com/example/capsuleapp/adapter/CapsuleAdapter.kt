package com.example.capsuleapp.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.capsuleapp.fragments.NotesFragment
import com.example.capsuleapp.fragments.QuizFragment
import com.example.capsuleapp.fragments.QuizResultFragment
import com.example.capsuleapp.fragments.VideoFragment

class CapsuleAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
            return 4
        }

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            when(position) {
                0 -> {
                    return VideoFragment()
                }
                1 -> {
                    return NotesFragment()
                }
                2 -> {
                    return QuizFragment()
                }
                3 -> {
                    return QuizResultFragment()
                }
                else -> {
                    return VideoFragment()
                }
        }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position) {
                0 -> {
                    return "Video"
                }
                1 -> {
                    return "Notes"
                }
                2 -> {
                    return "Quiz"
                }
                3 -> {
                    return "Quiz Result"
                }
            }
            return super.getPageTitle(position)
        }
}