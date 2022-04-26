package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.models.SignUpRequest
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpUseCase(private val callback: ResponseCallback) {
    fun execute(name : String, email : String, password : String){
        ApiClient.getApiService().signUp(
            SignUpRequest(
                name = name,
                email = email,
                password = password,
                avatar = ""
            )
        ).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback.response(false)
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                callback.response(true)
            }
        })
    }
}