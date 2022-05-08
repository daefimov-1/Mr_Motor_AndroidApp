package com.example.mr_motor_.data.models

import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.google.gson.annotations.SerializedName

data class QuizResultResponse(
    @SerializedName("quizResults")
    val quizResults: List<QuizResultVO>
)