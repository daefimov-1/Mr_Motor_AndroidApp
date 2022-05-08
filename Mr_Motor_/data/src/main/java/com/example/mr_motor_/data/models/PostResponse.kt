package com.example.mr_motor_.data.models

import com.example.mr_motor_.domain.models.Post
import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("posts")
    var posts: List<Post> = listOf()
)