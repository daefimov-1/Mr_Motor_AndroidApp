package com.example.mr_motor_.data.repository

import android.content.Context
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.repository.UserRepository

class UserRepositoryImpl(context: Context) : UserRepository {

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