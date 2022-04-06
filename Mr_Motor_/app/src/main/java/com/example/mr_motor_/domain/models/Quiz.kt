package com.example.mr_motor_.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Quiz(
    val id : Int,
    val title : String,
    val image : Int
) : Parcelable {
}