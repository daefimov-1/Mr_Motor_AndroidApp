package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostResultOfQuiz(private val userRepository: UserRepository) {

    fun execute(right_answers: Int, quiz_id: Long) {
        ApiClient.getApiService()
            .postResultOfQuiz(right_answers, quiz_id, userRepository.getAuthToken())
            .enqueue(object :
                Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                }
            })

    }
}