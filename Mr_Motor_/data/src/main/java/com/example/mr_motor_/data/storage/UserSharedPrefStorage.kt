package com.example.mr_motor_.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.domain.models.UserResponse
import com.google.gson.Gson

class UserSharedPrefStorage(context: Context) : UserStorage {
    private var prefs: SharedPreferences = context.getSharedPreferences("Mr_Motor_", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_DETAILS = "user_details"
    }

    /**
     * Function to save auth token
     */
    override fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    override fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    /**
     * Function to save details about user
     */
    override fun saveUser(user: UserResponse){
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER_DETAILS, json)
        editor.apply()
    }

    /**
     * Function to fetch user details
     */
    override fun fetchUser(): UserResponse? {
        val gson = Gson()
        val json: String? = prefs.getString(USER_DETAILS, null)
        return gson.fromJson(json, UserResponse::class.java)
    }

    /**
     * Function to delete user details and token
     */
    override fun deleteUserData() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove(USER_DETAILS)
        editor.apply()
    }
}