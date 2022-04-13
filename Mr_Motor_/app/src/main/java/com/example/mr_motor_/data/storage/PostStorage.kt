package com.example.mr_motor_.data.storage

import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.objects.PostType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface PostStorage {
    fun savePostsArray(list: List<Post>?, type: PostType)
    fun fetchPostsList(type: PostType): List<Post>
}