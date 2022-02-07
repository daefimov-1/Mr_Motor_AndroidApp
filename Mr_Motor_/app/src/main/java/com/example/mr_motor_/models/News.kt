package com.example.mr_motor_.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class News(
    val id : Int,
    val title : String,
    val text : String,
    val date : Int,
    val linq : String,
    val favourite : Boolean
) : Parcelable {
}