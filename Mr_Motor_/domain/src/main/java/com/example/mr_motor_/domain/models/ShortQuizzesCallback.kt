package com.example.mr_motor_.domain.models

import com.example.mr_motor_.domain.models.quiz.ShortQuizVO


interface ShortQuizzesCallback {
    fun response(result : List<ShortQuizVO>?)
}