package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.ForgotPasswordRequest
import com.example.mr_motor_.domain.models.login.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordUseCase() {

    fun execute(email: String, resultLiveMutable : MutableLiveData<Boolean>)  {
        ApiClient.getApiService().forgotPassword(ForgotPasswordRequest(email)).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
                resultLiveMutable.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                resultLiveMutable.value = true
            }
        })
    }
}