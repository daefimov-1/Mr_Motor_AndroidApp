package com.example.mr_motor_.presentation.tasks.myQuizes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.usecase.GetQuizzesResultsUseCase

class MyQuizzesResultPageViewModel(
    private val getQuizzesResultsUseCase: GetQuizzesResultsUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<List<QuizResultVO>>()
    val resultLive: LiveData<List<QuizResultVO>> = resultLiveMutable

    fun getAllResults() {
        getQuizzesResultsUseCase.execute(resultLiveMutable = resultLiveMutable)
    }
}