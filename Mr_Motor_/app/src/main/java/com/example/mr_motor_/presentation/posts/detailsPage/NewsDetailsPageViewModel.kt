package com.example.mr_motor_.presentation.posts.detailsPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.usecase.LikeUseCase

class NewsDetailsPageViewModel(
    private val likeUseCase: LikeUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<Boolean>()
    val resultLive: LiveData<Boolean> = resultLiveMutable

    fun like(newsId: Long) {
        likeUseCase.execute(newsId = newsId, resultLiveMutable = resultLiveMutable)
    }
}