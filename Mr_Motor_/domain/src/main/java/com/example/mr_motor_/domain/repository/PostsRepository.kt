package com.example.mr_motor_.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostType

interface PostsRepository {
    fun likePost(newsId: Long, resultLiveMutable: MutableLiveData<Boolean>)
    fun getLikedPosts(postsListMutableLiveData: MutableLiveData<List<Post>>)
    fun loadingPosts(resultLiveMutable: MutableLiveData<Boolean>, postType: PostType)
    fun getPosts(postsListMutableLiveData: MutableLiveData<List<Post>>, postType: PostType)
}