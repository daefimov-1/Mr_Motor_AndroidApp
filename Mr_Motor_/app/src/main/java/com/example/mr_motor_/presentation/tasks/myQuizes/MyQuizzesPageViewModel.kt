package com.example.mr_motor_.presentation.tasks.myQuizes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.usecase.GetUserQuizzesUseCase

class MyQuizzesPageViewModel(
    private val getUserQuizzesUseCase: GetUserQuizzesUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<List<ShortQuizVO>>()
    val resultLive: LiveData<List<ShortQuizVO>> = resultLiveMutable

    fun getUserQuizzes() {
        getUserQuizzesUseCase.execute(resultLiveMutable = resultLiveMutable)
    }
}