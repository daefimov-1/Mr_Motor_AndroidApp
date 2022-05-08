package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.repository.QuizRepository

class GetQuizUseCase(private val quizRepository: QuizRepository) {

    fun execute(id_quiz: Long, resultLiveMutable: MutableLiveData<QuizVO>) {
        quizRepository.getQuizById(id_quiz = id_quiz, resultLiveMutable = resultLiveMutable)
    }
}
