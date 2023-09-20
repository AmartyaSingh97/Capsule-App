package com.example.capsuleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.capsuleapp.R


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_home, container, false)

        var cardView : androidx.cardview.widget.CardView? = view?.findViewById(R.id.cardView)

        cardView?.setOnClickListener {

            val builder : AlertDialog.Builder? = context?.let { it1 -> AlertDialog.Builder(it1) }
            builder?.setTitle("Capsule")
            builder?.setMessage("Are you sure you want to start this, you must complete this in the given time limit?")
            builder?.setPositiveButton("Yes") { dialog, which ->
                val fragment = CapsuleFragment()
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, fragment)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }
            builder?.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            builder?.show()
        }


        return view
    }


}