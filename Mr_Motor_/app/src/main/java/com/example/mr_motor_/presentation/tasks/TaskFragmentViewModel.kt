package com.example.mr_motor_.presentation.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.usecase.LoadQuizzesUseCase

class TaskFragmentViewModel(
    private val loadQuizzesUseCase: LoadQuizzesUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<List<ShortQuizVO>>()
    val resultLive: LiveData<List<ShortQuizVO>> = resultLiveMutable

    fun getQuizzes() {
        loadQuizzesUseCase.execute(resultLiveMutable = resultLiveMutable)
    }
}