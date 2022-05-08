package com.example.mr_motor_.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.usecase.LoadPostsUseCase

class StartAppViewModel(
    private val loadPostsUseCase: LoadPostsUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<Boolean>()
    val resultLive : LiveData<Boolean> = resultLiveMutable

    private val lastResultLiveMutable = MutableLiveData<Boolean>()
    val lastResultLive : LiveData<Boolean> = resultLiveMutable

    fun loadAllPosts(){
        loadPostsUseCase.execute(resultLiveMutable, PostType.NEWS)
        loadPostsUseCase.execute(resultLiveMutable, PostType.CAR)
        loadPostsUseCase.execute(resultLiveMutable, PostType.RACER)
        loadPostsUseCase.execute(lastResultLiveMutable, PostType.COMPETITION)
    }
}