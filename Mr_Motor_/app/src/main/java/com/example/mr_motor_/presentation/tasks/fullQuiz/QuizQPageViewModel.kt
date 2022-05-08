package com.example.mr_motor_.presentation.tasks.fullQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.usecase.GetQuizUseCase

class QuizQPageViewModel(
    private val getQuizUseCase: GetQuizUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<QuizVO>()
    val resultLive: LiveData<QuizVO> = resultLiveMutable

    fun getQuiz(id_quiz: Long) {
        getQuizUseCase.execute(id_quiz = id_quiz, resultLiveMutable = resultLiveMutable)
    }
}