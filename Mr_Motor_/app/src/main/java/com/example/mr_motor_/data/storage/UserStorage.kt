package com.example.mr_motor_.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.UserResponse
import com.google.gson.Gson

interface UserStorage {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String?
    fun saveUser(user: UserResponse)
    fun fetchUser(): UserResponse?
    fun deleteUserData()
}