package com.example.mr_motor_.models

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("posts")
    var posts: List<Post> = listOf()
)