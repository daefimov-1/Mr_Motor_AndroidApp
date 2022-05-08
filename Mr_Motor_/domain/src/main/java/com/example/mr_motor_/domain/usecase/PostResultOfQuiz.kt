package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.repository.QuizRepository

class PostResultOfQuiz(private val quizRepository: QuizRepository) {

    fun execute(right_answers: Int, quiz_id: Long) {
        quizRepository.updateResultOfQuiz(right_answers = right_answers, quiz_id = quiz_id)
    }
}