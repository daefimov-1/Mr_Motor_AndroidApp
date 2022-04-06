package com.example.mr_motor_.domain.usecase

import android.util.Log
import com.example.mr_motor_.domain.models.LoginRequest
import com.example.mr_motor_.domain.models.LoginResponse
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUseCase(private val userRepository: UserRepository, private val callback: ResponseCallback) {

    private var token : String? = null
    fun execute(email : String, password : String) {

        ApiClient.getApiService().login(LoginRequest(email = email, password = password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("SAVE_TOKEN", "failed")
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()
                    if(loginResponse != null){
                        userRepository.saveAuthToken(loginResponse!!.token)
                        token = loginResponse!!.token
                        Log.e("SAVE_TOKEN", userRepository.getAuthToken())
                        loadDetails()
                    }

                }
            })



    }

    private fun loadDetails(){
        ApiClient.getApiService().get_details(token).enqueue(object :
            Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("LOADFROMSERVER", "load failed")
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userResponse = response.body()
                if (userResponse != null) {
                    Log.e("LOADFROMSERVER", "load good")
                    userRepository.saveUserData(userResponse)
                    callback.response()
                }

            }
        })
    }
}