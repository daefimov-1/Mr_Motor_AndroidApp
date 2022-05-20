package com.example.mr_motor_.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.data.datasource.storage.UserStorage
import com.example.mr_motor_.data.models.*
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.repository.UserRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
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
        with(ApiClient) {
            getApiService().login(LoginRequest(email = email, password = password))
                .observeOn(Schedulers.io())
                .doOnNext { loginResponse -> tokenStorage.saveAuthToken(loginResponse.token) }
                .flatMap { loginResponse ->  getApiService().get_details(loginResponse.token) }
                .observeOn(Schedulers.io())
                .doOnNext { userResponse -> userStorage.saveUser(userResponse) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<UserResponse> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(t: UserResponse?) {
                        resultLiveMutable.value = t != null
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("USER_REPO", e.toString())
                        resultLiveMutable.value = false
                    }

                    override fun onComplete() {
                    }

                })
        }
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