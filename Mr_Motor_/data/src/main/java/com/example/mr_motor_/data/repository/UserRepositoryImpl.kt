package com.example.mr_motor_.data.repository

import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.data.storage.UserStorage
import com.example.mr_motor_.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveAuthToken(token : String) {
        userStorage.saveAuthToken(token = token)
    }

    override fun getAuthToken() : String {
        return userStorage.fetchAuthToken().toString()
    }

    override fun saveUserData(user: UserResponse) {
        userStorage.saveUser(user = user)
    }
}