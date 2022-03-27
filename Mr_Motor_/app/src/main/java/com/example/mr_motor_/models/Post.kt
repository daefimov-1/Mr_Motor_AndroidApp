package com.example.mr_motor_.models

import android.graphics.Bitmap
import android.os.Parcelable
import com.example.mr_motor_.objects.PostType
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var id: Long = 0,
    var title: String = "Title",
    var source: String = "",
    var content: String = "",
    var type: PostType = PostType.NEWS,
    var thumbnail: String = "",
) : Parcelable {
}