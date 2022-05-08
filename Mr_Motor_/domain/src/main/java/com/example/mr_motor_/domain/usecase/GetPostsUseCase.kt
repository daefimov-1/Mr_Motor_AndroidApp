package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.repository.PostsRepository

class GetPostsUseCase(private val postsRepository: PostsRepository) {
    fun execute(postsListMutableLiveData: MutableLiveData<List<Post>>, postType: PostType) {
        postsRepository.getPosts(postsListMutableLiveData = postsListMutableLiveData, postType = postType)
    }
}