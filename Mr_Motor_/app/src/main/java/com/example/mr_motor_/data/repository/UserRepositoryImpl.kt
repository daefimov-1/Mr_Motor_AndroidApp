package com.example.mr_motor_.data.repository

import android.content.Context
import android.util.Log
import com.example.mr_motor_.domain.models.LoginRequest
import com.example.mr_motor_.domain.models.LoginResponse
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl(private val context: Context) : UserRepository {

    val sessionManager: SessionManager = SessionManager(context)

    override fun saveAuthToken(token : String) {
        sessionManager.saveAuthToken(token = token)
    }

    override fun getAuthToken() : String {
        return sessionManager.fetchAuthToken().toString()
    }

    override fun saveUserData(user: UserResponse) {
        sessionManager.saveUser(user = user)
    }
}