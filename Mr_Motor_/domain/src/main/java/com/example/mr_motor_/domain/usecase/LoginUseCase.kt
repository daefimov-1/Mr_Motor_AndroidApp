package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.LoginRequest
import com.example.mr_motor_.domain.models.LoginResponse
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUseCase(private val userRepository: UserRepository) {
    private var token : String? = null
    fun execute(email : String, password : String, resultLiveMutable : MutableLiveData<Boolean>) {
        ApiClient.getApiService().login(LoginRequest(email = email, password = password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                    resultLiveMutable.value = false
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()
                    if(loginResponse != null){
                        userRepository.saveAuthToken(loginResponse.token)
                        token = loginResponse.token
                        loadDetails(resultLiveMutable)
                    }

                }
            })

    }

    private fun loadDetails(resultLiveMutable : MutableLiveData<Boolean>) {
        ApiClient.getApiService().get_details(token).enqueue(object :
            Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                resultLiveMutable.value = false
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userResponse = response.body()
                if (userResponse != null) {
                    userRepository.saveUserData(userResponse)
                    resultLiveMutable.value = true
                }

            }
        })
    }
}