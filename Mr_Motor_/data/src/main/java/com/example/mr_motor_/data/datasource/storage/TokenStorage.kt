package com.example.mr_motor_.data.datasource.storage

interface TokenStorage {
    fun saveAuthToken(token: String)
    fun fetchAuthToken(): String?
}