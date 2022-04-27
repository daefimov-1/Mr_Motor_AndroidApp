package com.example.mr_motor_.domain.usecase

import android.util.Log
import com.example.mr_motor_.domain.models.QuizCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetQuizUseCase(private val userRepository: UserRepository, private val callback: QuizCallback) {

    fun execute(id_quiz: Long){
        ApiClient.getApiService().get_quiz(id_quiz, userRepository.getAuthToken())
            .enqueue(object :
                Callback<QuizVO> {
                override fun onFailure(call: Call<QuizVO>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("LIST_QUESTIONS_API", "doesn't work")
                }

                override fun onResponse(call: Call<QuizVO>, response: Response<QuizVO>) {
                    callback.response(response.body())
                }
            })

    }
}
