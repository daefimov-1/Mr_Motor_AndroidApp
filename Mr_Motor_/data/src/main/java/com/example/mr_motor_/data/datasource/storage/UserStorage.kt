package com.example.mr_motor_.data.datasource.storage

import com.example.mr_motor_.domain.models.UserResponse

interface UserStorage {
    fun saveUser(user: UserResponse)
    fun fetchUser(): UserResponse?
    fun deleteUserData()
}