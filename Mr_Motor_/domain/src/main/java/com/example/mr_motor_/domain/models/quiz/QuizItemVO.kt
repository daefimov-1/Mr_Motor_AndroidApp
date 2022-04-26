package com.example.mr_motor_.domain.models.quiz

import com.google.gson.annotations.SerializedName

data class QuizItemVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("question")
    val question: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("quizAnswers")
    val quizAnswers: List<QuizAnswerVO>
)