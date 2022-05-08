package com.example.mr_motor_.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.UserResponse

interface UserRepository {
    fun login(email: String, password: String, resultLiveMutable: MutableLiveData<Boolean>)
    fun signUp(
        name: String,
        email: String,
        password: String,
        resultLiveMutable: MutableLiveData<Boolean>
    )
    fun forgotPassword(email: String, resultLiveMutable: MutableLiveData<Boolean>)
    fun updateUserData(
        name: String,
        email: String,
        avatarString: String?,
        userAvatar: String,
        resultLiveMutable: MutableLiveData<Boolean>
    )
    fun getUserData(): UserResponse?
    fun getAuthToken(): String
    fun deleteUserData()
}