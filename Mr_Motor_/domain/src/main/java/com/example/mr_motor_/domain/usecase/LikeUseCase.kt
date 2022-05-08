package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.PostsRepository

class LikeUseCase(private val postsRepository: PostsRepository) {

    fun execute(newsId: Long, resultLiveMutable: MutableLiveData<Boolean>) {
        postsRepository.likePost(newsId = newsId, resultLiveMutable = resultLiveMutable)
    }
}