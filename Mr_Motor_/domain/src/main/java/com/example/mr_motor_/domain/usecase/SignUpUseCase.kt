package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.SignUpRequest
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpUseCase() {
    fun execute(name : String, email : String, password : String, resultLiveMutable : MutableLiveData<Boolean>) {
        ApiClient.getApiService().signUp(
            SignUpRequest(
                name = name,
                email = email,
                password = password,
                avatar = ""
            )
        ).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                resultLiveMutable.value = false
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                resultLiveMutable.value = true
            }
        })
    }
}