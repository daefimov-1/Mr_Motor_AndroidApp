package com.example.mr_motor_.domain.models.quiz

import com.google.gson.annotations.SerializedName

data class QuizAnswerVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("isCorrect")
    val isCorrect: Boolean
)