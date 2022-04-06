package com.example.mr_motor_.data.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_DETAILS = "user_details"
        const val NEWS_LIST = "news_list"
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

    /**
     * Function to delete user details and token
     */
    fun deleteUserData() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.remove(USER_DETAILS)
        editor.apply()
    }

    /**
     * Function to save news when they are uploaded on splash screen
     */
    fun saveNewsArray(list: List<Post>?){
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(NEWS_LIST, json)
        editor.apply()
    }

    /**
     * Function to fetch news list
     */
    fun fetchNewsList(): List<Post> {
        val gson = Gson()
        val json: String? = prefs.getString(NEWS_LIST, null)
        return gson.fromJson(json, object : TypeToken<List<Post>>() {}.type)
    }
}