package com.example.mr_motor_.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO

interface QuizRepository {
    fun getQuizById(id_quiz: Long, resultLiveMutable: MutableLiveData<QuizVO>)
    fun getQuizzesResults(resultLiveMutable: MutableLiveData<List<QuizResultVO>>)
    fun getUserQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>)
    fun getAllShortQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>)
    fun updateResultOfQuiz(right_answers: Int, quiz_id: Long)
}