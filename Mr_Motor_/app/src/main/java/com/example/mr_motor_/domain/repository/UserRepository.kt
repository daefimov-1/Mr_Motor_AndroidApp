package com.example.mr_motor_.domain.repository

import com.example.mr_motor_.domain.models.LoginRequest
import com.example.mr_motor_.domain.models.UserResponse

interface UserRepository {
    fun saveAuthToken(token: String)
    fun getAuthToken() : String
    fun saveUserData(user : UserResponse)
}