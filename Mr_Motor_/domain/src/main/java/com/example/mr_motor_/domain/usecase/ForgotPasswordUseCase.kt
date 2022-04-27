package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.ForgotPasswordRequest
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordUseCase(private val callback: ResponseCallback) {

    fun execute(email: String){
        ApiClient.getApiService().forgotPassword(ForgotPasswordRequest(email)).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
                callback.response(false)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                callback.response(true)
            }
        })

    }
}