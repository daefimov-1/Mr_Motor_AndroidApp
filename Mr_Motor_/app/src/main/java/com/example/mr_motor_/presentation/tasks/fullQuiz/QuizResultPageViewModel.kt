package com.example.mr_motor_.presentation.tasks.fullQuiz

import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.models.quiz.ResultQuiz
import com.example.mr_motor_.domain.usecase.PostResultOfQuiz

class QuizResultPageViewModel(
    private val postResultUseCase: PostResultOfQuiz
) : ViewModel() {

    fun postResult(result: ResultQuiz) {
        postResultUseCase.execute(result.right_answers, result.quiz_id)
    }
}