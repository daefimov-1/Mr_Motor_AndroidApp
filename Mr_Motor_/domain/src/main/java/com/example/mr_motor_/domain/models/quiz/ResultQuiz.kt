package com.example.mr_motor_.domain.models.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultQuiz(
    val right_answers : Int,
    val number_of_questions : Int,
    val quiz_name : String,
    val quiz_id : Long
) : Parcelable