package com.example.mr_motor_.data.datasource.storage

import android.content.Context
import android.content.SharedPreferences

class TokenSharedPrefStorage(context: Context) : TokenStorage {
    private var prefs: SharedPreferences =
        context.getSharedPreferences("Mr_Motor_", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    override fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(UserSharedPrefStorage.USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    override fun fetchAuthToken(): String? {
        return prefs.getString(UserSharedPrefStorage.USER_TOKEN, null)
    }
}