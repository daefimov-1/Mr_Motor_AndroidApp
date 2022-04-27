package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.ShortQuizzesCallback
import com.example.mr_motor_.domain.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserQuizzesUseCase(private val userRepository: UserRepository, private val callback: ShortQuizzesCallback) {
    fun execute(){
        ApiClient.getApiService().getMyQuizzes(userRepository.getAuthToken()).enqueue(object :
            Callback<ShortQuizesResponse> {
            override fun onFailure(call: Call<ShortQuizesResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ShortQuizesResponse>, response: Response<ShortQuizesResponse>) {
                callback.response(response.body()?.quizzes)
            }
        })

    }
}