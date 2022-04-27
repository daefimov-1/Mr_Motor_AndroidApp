package com.example.mr_motor_.domain.models

import com.example.mr_motor_.domain.models.quiz.QuizResultVO

interface QuizResultsCallback {
    fun response(result : List<QuizResultVO>?)
}