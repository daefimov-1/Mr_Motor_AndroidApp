package com.example.mr_motor_.domain.models

import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.google.gson.annotations.SerializedName

data class ShortQuizesResponse (
    @SerializedName("quizzes")
    var quizzes: List<ShortQuizVO> = listOf()
)