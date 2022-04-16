package com.example.mr_motor_.domain.models.quiz

import com.google.gson.annotations.SerializedName

data class QuizResultVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("achieved")
    val achieved: Int,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("quiz")
    val quiz: ShortQuizVO
)