package com.example.mr_motor_.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.data.datasource.storage.UserStorage
import com.example.mr_motor_.data.models.*
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(
    private val userStorage: UserStorage,
    private val tokenStorage: TokenStorage
) : UserRepository {

    override fun login(
        email: String,
        password: String,
        resultLiveMutable: MutableLiveData<Boolean>
    ) {
        ApiClient.getApiService().login(LoginRequest(email = email, password = password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.printStackTrace()
                    resultLiveMutable.value = false
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        tokenStorage.saveAuthToken(loginResponse.token)
                        loginSecondPart(loginResponse.token, resultLiveMutable)
                    }

                }
            })
    }

    private fun loginSecondPart(token: String, resultLiveMutable: MutableLiveData<Boolean>) {
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
                    userStorage.saveUser(userResponse)
                    resultLiveMutable.value = true
                }

            }
        })
    }

    override fun signUp(
        name: String,
        email: String,
        password: String,
        resultLiveMutable: MutableLiveData<Boolean>
    ) {
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

    override fun forgotPassword(email: String, resultLiveMutable: MutableLiveData<Boolean>) {
        ApiClient.getApiService().forgotPassword(ForgotPasswordRequest(email))
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.printStackTrace()
                    resultLiveMutable.value = false
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    resultLiveMutable.value = true
                }
            })
    }

    override fun updateUserData(
        name: String,
        email: String,
        avatarString: String?,
        userAvatar: String,
        resultLiveMutable: MutableLiveData<Boolean>
    ) {
        ApiClient.getApiService().update(
            SignUpRequest(
                name = name,
                email = email,
                password = "",
                avatar = (avatarString ?: userAvatar)

            ), tokenStorage.fetchAuthToken()
        ).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                resultLiveMutable.value = false
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.body() != null) {
                    userStorage.saveUser(response.body()!!)
                    resultLiveMutable.value = true
                }
            }
        })
    }

    override fun getAuthToken(): String {
        return tokenStorage.fetchAuthToken().toString()
    }

    override fun getUserData(): UserResponse? {
        return userStorage.fetchUser()
    }

    override fun deleteUserData() {
        userStorage.deleteUserData()
    }


}