package com.example.capsuleapp.models

data class Quiz(
    val id: Int,
    val question: String,
    val answer: Int,
    val options: List<String>
)
