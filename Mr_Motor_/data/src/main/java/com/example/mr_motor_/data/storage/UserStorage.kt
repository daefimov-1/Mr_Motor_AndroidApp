package com.example.mr_motor_.data.storage

import com.example.mr_motor_.domain.models.UserResponse

interface UserStorage {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String?
    fun saveUser(user: UserResponse)
    fun fetchUser(): UserResponse?
    fun deleteUserData()
}