package com.example.mr_motor_.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var id: Long = 0,
    var title: String = "Title",
    var source: String = "",
    var content: String = "",
    var type: PostType = PostType.NEWS,
    var thumbnail: String = "",
    var like: Boolean = false
) : Parcelable {
}