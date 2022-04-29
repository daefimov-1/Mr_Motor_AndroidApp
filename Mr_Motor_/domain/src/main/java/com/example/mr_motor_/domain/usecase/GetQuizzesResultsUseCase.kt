package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.QuizResultResponse
import com.example.mr_motor_.domain.models.QuizResultsCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetQuizzesResultsUseCase(private val userRepository: UserRepository, private val callback: QuizResultsCallback) {

    fun execute(){
        ApiClient.getApiService().getQuizesResults(userRepository.getAuthToken()).enqueue(object :
            Callback<QuizResultResponse> {
            override fun onFailure(call: Call<QuizResultResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<QuizResultResponse>, response: Response<QuizResultResponse>) {
                callback.response(response.body()?.quizResults)
            }
        })
    }
}