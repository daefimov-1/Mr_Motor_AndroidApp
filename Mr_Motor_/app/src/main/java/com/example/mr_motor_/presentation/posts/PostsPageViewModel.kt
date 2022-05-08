package com.example.mr_motor_.presentation.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.usecase.GetLikedPostsUseCase
import com.example.mr_motor_.domain.usecase.GetPostsUseCase

class PostsPageViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val getLikedPostsUseCase: GetLikedPostsUseCase
) : ViewModel() {

    private val postsListMutableLiveData = MutableLiveData<List<Post>>()
    val postsListLiveData : LiveData<List<Post>> = postsListMutableLiveData

    fun getCars(){
        getPostsUseCase.execute(postsListMutableLiveData = postsListMutableLiveData, postType = PostType.CAR)
    }

    fun getCompetitions(){
        getPostsUseCase.execute(postsListMutableLiveData = postsListMutableLiveData, postType = PostType.COMPETITION)
    }

    fun getNews(){
        getPostsUseCase.execute(postsListMutableLiveData = postsListMutableLiveData, postType = PostType.NEWS)
    }

    fun getRacers(){
        getPostsUseCase.execute(postsListMutableLiveData = postsListMutableLiveData, postType = PostType.RACER)
    }

    fun getLikedPosts(){
        getLikedPostsUseCase.execute(postsListMutableLiveData = postsListMutableLiveData)
    }

}