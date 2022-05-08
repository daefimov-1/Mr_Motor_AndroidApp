package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.repository.QuizRepository

class LoadQuizzesUseCase(private val quizRepository: QuizRepository) {
    fun execute(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>){
        quizRepository.getAllShortQuizzes(resultLiveMutable = resultLiveMutable)
    }
}