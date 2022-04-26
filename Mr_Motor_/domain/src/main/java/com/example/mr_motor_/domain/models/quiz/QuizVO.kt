package com.example.mr_motor_.domain.models.quiz

import com.example.mr_motor_.domain.models.UserResponse
import com.google.gson.annotations.SerializedName

data class QuizVO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("timer")
    val timer: Int,
    @SerializedName("author")
    val author: UserResponse,
    @SerializedName("quizItems")
    val quizItems: List<QuizItemVO>
)