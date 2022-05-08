package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.repository.PostsRepository

class GetLikedPostsUseCase(private val postsRepository: PostsRepository) {

    fun execute(postsListMutableLiveData: MutableLiveData<List<Post>>) {
        postsRepository.getLikedPosts(postsListMutableLiveData = postsListMutableLiveData)
    }
}