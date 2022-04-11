package com.example.mr_motor_.data.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.objects.PostType
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
        const val COMPETITION_LIST = "competition_list"
        const val CAR_LIST = "car_list"
        const val RACER_LIST = "racer_list"
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
     * Function to save posts when they are uploaded on splash screen
     */
    fun savePostsArray(list: List<Post>?, type: PostType){
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        when(type){
            PostType.NEWS -> editor.putString(NEWS_LIST, json)
            PostType.COMPETITION -> editor.putString(COMPETITION_LIST, json)
            PostType.CAR -> editor.putString(CAR_LIST, json)
            PostType.RACER -> editor.putString(RACER_LIST, json)
        }

        editor.apply()
    }

    /**
     * Function to fetch posts list
     */
    fun fetchPostsList(type: PostType): List<Post> {
        val gson = Gson()
        var json: String? = null
        when(type){
            PostType.NEWS -> json = prefs.getString(NEWS_LIST, null)
            PostType.COMPETITION -> json = prefs.getString(COMPETITION_LIST, null)
            PostType.CAR -> json = prefs.getString(CAR_LIST, null)
            PostType.RACER -> json = prefs.getString(RACER_LIST, null)
        }

        return gson.fromJson(json, object : TypeToken<List<Post>>() {}.type)
    }
}