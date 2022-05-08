package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.repository.PostsRepository

class LoadPostsUseCase(private val postsRepository: PostsRepository) {

    fun execute(resultLiveMutable: MutableLiveData<Boolean>, postType: PostType){
        postsRepository.loadingPosts(resultLiveMutable, postType)
    }
}