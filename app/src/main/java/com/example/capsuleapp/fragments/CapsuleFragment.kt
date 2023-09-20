package com.example.capsuleapp.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.capsuleapp.R
import com.example.capsuleapp.adapter.CapsuleAdapter
import com.example.capsuleapp.utils.SnackBuilder
import com.google.android.material.tabs.TabLayout

class CapsuleFragment : Fragment() {

    private lateinit var timerTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var tablayout: TabLayout
    private lateinit var viewPagerTab : ViewPager
    private lateinit var nextPage : TextView
    private lateinit var cardView: CardView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_capsule, container, false)

        val imageView = view.findViewById<ImageView>(R.id.back_BTN)
        timerTextView = view.findViewById(R.id.time)
        tablayout = view.findViewById(R.id.tablayout)
        viewPagerTab = view.findViewById(R.id.viewPagerTab)
        nextPage = view.findViewById(R.id.next)
        cardView = view.findViewById(R.id.cardView)

        sharedPreferences = activity?.getSharedPreferences("sharedPrefs", 0)!!

        val check = sharedPreferences.getInt("check", 0)

        val adapter : CapsuleAdapter = CapsuleAdapter(childFragmentManager)
        viewPagerTab.adapter = adapter
        tablayout.setupWithViewPager(viewPagerTab)

        nextPage.text = adapter.getPageTitle(viewPagerTab.currentItem+1)

        viewPagerTab.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                if (viewPagerTab.currentItem == 3) {
                    nextPage.text = "Finish"
                }
                else {
                    nextPage.text = adapter.getPageTitle(viewPagerTab.currentItem+1)
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) { }

            override fun onPageSelected(position: Int) { }
        })

        cardView.setOnClickListener {
            if (viewPagerTab.currentItem == 3) {
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().remove(this@CapsuleFragment).commit()
                fragmentManager.beginTransaction().add(R.id.fragment_container, HomeFragment()).commit()
//                val editor = sharedPreferences.edit()
//                editor.clear()
//                editor.apply()
            }
            else {
                viewPagerTab.currentItem = viewPagerTab.currentItem + 1
            }
        }


        imageView.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            activity?.onBackPressed()
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val initialTimeMillis = 10 * 60 * 1000L

        countDownTimer = object : CountDownTimer(initialTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().remove(this@CapsuleFragment).commit()
                fragmentManager.beginTransaction().add(R.id.fragment_container, HomeFragment()).commit()
                SnackBuilder.getInstance()
                    .setmCtx(context)
                    .setMargin(12, 12, 12, 12)
                    .setMessage("Time's up!")
                    .setShowLength(SnackBuilder.LENGTH_LONG)
                    .setMessageColor(R.color.white)
                    .setBackgroundType(2)
                    .show()
            }
        }

        countDownTimer.start()
    }



}