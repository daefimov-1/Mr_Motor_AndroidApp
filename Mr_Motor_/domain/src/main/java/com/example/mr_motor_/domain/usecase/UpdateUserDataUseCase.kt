package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.*
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateUserDataUseCase(private val userRepository: UserRepository, private val callback: ResponseCallback) {

    fun execute(name: String, email: String, avatarString: String?, userAvatar: String){
        ApiClient.getApiService().update(
            SignUpRequest(
                name = name,
                email = email,
                password = "",
                avatar = (avatarString ?: userAvatar)

            ), userRepository.getAuthToken()
        ).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                callback.response(false)
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if(response.body() != null){
                    userRepository.saveUserData(response.body()!!)
                    callback.response(true)
                }
            }
        })

    }
}