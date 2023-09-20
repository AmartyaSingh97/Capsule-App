package com.example.capsuleapp.fragments

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capsuleapp.R
import com.example.capsuleapp.data.Constants
import com.example.capsuleapp.models.Quiz
import com.example.capsuleapp.utils.SnackBuilder
import com.google.android.material.card.MaterialCardView

class QuizFragment : Fragment() {

    private var currPosition: Int = 1
    private var questionsList: ArrayList<Quiz>? = null
    private var selectedOptionPosition: Int = 0
    private lateinit var question: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView
    private lateinit var tvOption1 : MaterialCardView
    private lateinit var tvOption2 : MaterialCardView
    private lateinit var tvOption3 : MaterialCardView
    private lateinit var checkAnswerButton: MaterialCardView
    private lateinit var nextQuestion: MaterialCardView
    private var size : Int = 0
    private lateinit var questionNumber : TextView
    private var totalPoints : Int = 0
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var btnText : TextView
    private lateinit var checkScore: MaterialCardView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        questionsList = Constants.getQuestions()
        size = questionsList!!.size
        question = view?.findViewById(R.id.question)!!
        optionOne = view.findViewById(R.id.option1)!!
        optionTwo = view.findViewById(R.id.option2)!!
        optionThree = view.findViewById(R.id.option3)!!
        questionNumber = view.findViewById(R.id.question_number)!!

        tvOption1 = view.findViewById(R.id.tvOption1)
        tvOption2 = view.findViewById(R.id.tvOption2)
        tvOption3 = view.findViewById(R.id.tvOption3)

        btnText = view.findViewById(R.id.btn_text)
        checkAnswerButton = view.findViewById(R.id.checkAnswer)
        nextQuestion = view.findViewById(R.id.nextQuestion)
        checkScore = view.findViewById(R.id.checkScore)

        sharedPreferences = activity?.getSharedPreferences("sharedPrefs", 0)!!

        setQuestion()

        checkAnswerButton.setOnClickListener {
            checkAnswer()

        }

        nextQuestion.setOnClickListener {

            currPosition++

            removeColour(tvOption1)
            removeColour(tvOption2)
            removeColour(tvOption3)

            if(currPosition == size){
                setQuestion()
                nextQuestion.visibility = View.GONE

            }else{
            setQuestion()
        }
        }

        tvOption1.setOnClickListener {
            changeColour(tvOption1)
        }
        tvOption2.setOnClickListener{
            changeColour(tvOption2)
    }
        tvOption3.setOnClickListener{
            changeColour(tvOption3)
    }

        checkScore.setOnClickListener {
            val fragment = QuizResultFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()

            Bundle().apply {
                putInt("totalPoints", totalPoints)
                fragment.arguments = this
            }
            fragmentTransaction?.replace(R.id.fragment_container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }



        return view
    }
    private fun removeColour(tvOption: MaterialCardView?) {
        tvOption?.setCardBackgroundColor(Color.parseColor("#FFE5B4"))
        tvOption?.strokeColor = Color.parseColor("#FFFFFF")
        tvOption?.strokeWidth = 0
    }

    private fun changeColour(tvOption: MaterialCardView?) {
        if(tvOption == tvOption1){
            selectedOptionPosition = 1
            removeColour(tvOption2)
            removeColour(tvOption3)
        }
        else if(tvOption == tvOption2){
            selectedOptionPosition = 2
            removeColour(tvOption1)
            removeColour(tvOption3)
        }
        else if(tvOption == tvOption3){
            selectedOptionPosition = 3
            removeColour(tvOption2)
            removeColour(tvOption1)
        }
        tvOption?.setCardBackgroundColor(Color.parseColor("#363A43"))
        tvOption?.strokeColor = Color.parseColor("#323232")
        tvOption?.strokeWidth = 5
    }

    private fun checkAnswer(){
        val currQuestion = questionsList!![currPosition - 1]
        val correctAnswer = currQuestion.answer
        if(selectedOptionPosition-1 == correctAnswer){
            setGreen(selectedOptionPosition)
            totalPoints++

            if(currPosition == size){
                checkAnswerButton.visibility = View.GONE
                checkScore.visibility = View.VISIBLE
            }

        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("points", totalPoints)
        editor.apply()

//            if(currPosition == size){
//                editor.putInt("quiz", 1)
//                editor.apply()
//            }


            SnackBuilder.getInstance()
                .setmCtx(context)
                .setMargin(12, 12, 12, 12)
                .setMessage("Correct Answer!")
                .setShowLength(SnackBuilder.LENGTH_LONG)
                .setMessageColor(R.color.white)
                .setBackgroundType(3)
                .show()

        }else{
            setRed(selectedOptionPosition)
            SnackBuilder.getInstance()
                .setmCtx(context)
                .setMargin(12, 12, 12, 12)
                .setMessage("Please Try Again!")
                .setShowLength(SnackBuilder.LENGTH_LONG)
                .setMessageColor(R.color.white)
                .setBackgroundType(3)
                .show()
        }
    }

    private fun setRed(selectedOptionPosition: Int) {
        if(selectedOptionPosition == 1){
            tvOption1.strokeColor = Color.parseColor("#ffcc0000")
            tvOption1.strokeWidth = 8
        }else if(selectedOptionPosition == 2){
            tvOption2.strokeColor = (Color.parseColor("#ffcc0000"))
            tvOption2.strokeWidth = 8
        }else if(selectedOptionPosition == 3){
            tvOption3.strokeColor = (Color.parseColor("#ffcc0000"))
            tvOption3.strokeWidth = 8
        }
    }


    private fun setGreen(selectedOptionPosition: Int) {
        if(selectedOptionPosition == 1){
            tvOption1.strokeColor = Color.parseColor("#ff669900")
            tvOption1.strokeWidth = 8
        }else if(selectedOptionPosition == 2){
            tvOption2.strokeColor = (Color.parseColor("#ff669900"))
            tvOption2.strokeWidth = 8
        }else if(selectedOptionPosition == 3){
            tvOption3.strokeColor = (Color.parseColor("#ff669900"))
            tvOption3.strokeWidth = 8
        }
    }

    private fun setQuestion() {

        val currQuestion = questionsList!![currPosition - 1]

        questionNumber.text = "$currPosition"
        question.text = currQuestion.question
        optionOne.text = currQuestion.options[0]
        optionTwo.text = currQuestion.options[1]
        optionThree.text = currQuestion.options[2]

    }



}