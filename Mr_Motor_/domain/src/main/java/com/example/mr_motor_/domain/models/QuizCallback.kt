package com.example.mr_motor_.domain.models

import com.example.mr_motor_.domain.models.quiz.QuizVO

interface QuizCallback {
    fun response(result : QuizVO?)
}