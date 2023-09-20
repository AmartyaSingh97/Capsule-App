package com.example.capsuleapp.data

import com.example.capsuleapp.models.Quiz

object Constants {

    fun getQuestions(): ArrayList<Quiz>{
        val questionsList = ArrayList<Quiz>()

    val que1 = Quiz(
            1, "Which of the following statements is true concerning human blood?",
            0,
            listOf("The blood of all normal humans contains red and white cells, platelets, and plasma.", "Some human populations normally lack the ability to produce plasma.", "Proteins are not normal components of human blood.")
        )

    val que2 = Quiz(
        2, "Erythrocyte is another name for a:",
        1,
        listOf("Red blood cell", "White blood cell", "Platelet")
    )

    val que3 = Quiz(
        3, "Which of the following statements is true concerning the ABO blood groups?",
        1,
        listOf("The ABO blood groups are based on the presence or absence of specific carbohydrates on the surface of red blood cells.", "The ABO blood groups are based on the presence or absence of specific proteins on the surface of red blood cells.", "The ABO blood groups are based on the presence or absence of specific proteins on the surface of white blood cells.")
    )

    val que4 = Quiz(
        4, "Which of the following statements is true concerning the Rh blood groups?",
        2,
        listOf("The Rh blood groups are based on the presence or absence of specific proteins on the surface of red blood cells.", "The Rh blood groups are based on the presence or absence of specific carbohydrates on the surface of red blood cells.", "The Rh blood groups are based on the presence or absence of specific proteins on the surface of white blood cells.")
    )

    val que5 = Quiz(
        1,  "Which of the following are likely to increase in quantities when the body is under attack from bacteria?",
        1,
        listOf("erythrocytes", "leukocytes", "thrombocytes")
    )


        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)

        return questionsList
    }
}