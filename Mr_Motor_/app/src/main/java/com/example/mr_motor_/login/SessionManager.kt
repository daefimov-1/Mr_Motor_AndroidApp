package com.example.mr_motor_.login

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.R
import com.example.mr_motor_.models.UserResponse
import com.google.gson.Gson




/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_DETAILS = "user_details"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    /**
     * Function to save details about user
     */
    fun saveUser(user: UserResponse){
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER_DETAILS, json)
        editor.apply()
    }

    /**
     * Function to fetch user details
     */
    fun fetchUser(): UserResponse? {
        val gson = Gson()
        val json: String? = prefs.getString(USER_DETAILS, null)
        return gson.fromJson(json, UserResponse::class.java)
    }
}