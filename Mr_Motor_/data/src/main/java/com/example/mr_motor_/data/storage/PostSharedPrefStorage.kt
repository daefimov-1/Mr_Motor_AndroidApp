package com.example.mr_motor_.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.objects.PostType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostSharedPrefStorage(context: Context) : PostStorage {

    private var prefs: SharedPreferences = context.getSharedPreferences("Mr_Motor_", Context.MODE_PRIVATE)

    companion object {
        const val NEWS_LIST = "news_list"
        const val COMPETITION_LIST = "competition_list"
        const val CAR_LIST = "car_list"
        const val RACER_LIST = "racer_list"
    }

    override fun savePostsArray(list: List<Post>?, type: PostType){
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        when(type){
            PostType.NEWS -> editor.putString(SessionManager.NEWS_LIST, json)
            PostType.COMPETITION -> editor.putString(SessionManager.COMPETITION_LIST, json)
            PostType.CAR -> editor.putString(SessionManager.CAR_LIST, json)
            PostType.RACER -> editor.putString(SessionManager.RACER_LIST, json)
        }

        editor.apply()
    }

    /**
     * Function to fetch posts list
     */
    override fun fetchPostsList(type: PostType): List<Post> {
        val gson = Gson()
        var json: String? = null
        when(type){
            PostType.NEWS -> json = prefs.getString(SessionManager.NEWS_LIST, null)
            PostType.COMPETITION -> json = prefs.getString(SessionManager.COMPETITION_LIST, null)
            PostType.CAR -> json = prefs.getString(SessionManager.CAR_LIST, null)
            PostType.RACER -> json = prefs.getString(SessionManager.RACER_LIST, null)
        }

        return gson.fromJson(json, object : TypeToken<List<Post>>() {}.type)
    }
}