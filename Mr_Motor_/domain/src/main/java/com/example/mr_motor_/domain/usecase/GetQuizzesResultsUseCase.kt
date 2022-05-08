package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.repository.QuizRepository

class GetQuizzesResultsUseCase(private val quizRepository: QuizRepository) {

    fun execute(resultLiveMutable: MutableLiveData<List<QuizResultVO>>) {
        quizRepository.getQuizzesResults(resultLiveMutable = resultLiveMutable)
    }
}