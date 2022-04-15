package com.example.mr_motor_.domain.usecase

import android.util.Log
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.NewsRepository
import com.example.mr_motor_.domain.repository.UserRepository
import com.example.mr_motor_.presentation.MainActivity
import com.example.mr_motor_.presentation.NoInternetPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadNewsUseCase(private val newsRepository: NewsRepository, private val userRepository: UserRepository, private val callback: ResponseCallback) {

    fun execute(){
        if(userRepository.getAuthToken() == "null"){
            ApiClient.getLongerConnectionApiService().get_news().enqueue(object :
                Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("SPLASHSCREEN_APICLIENT", "news cannot be taken")
                    callback.response(false)
                }

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    newsRepository.loadNews(response.body()?.posts)
                    Log.d("SPLASHSCREEN_APICLIENT", "news taken")
                    callback.response(true)
                }
            })
        }
        else{
            ApiClient.getLongerConnectionApiService().getNewsWithToken(userRepository.getAuthToken()).enqueue(object :
                Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("SPLASHSCREEN_APICLIENT", "news cannot be taken")
                    callback.response(false)
                }

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    newsRepository.loadNews(response.body()?.posts)
                    Log.d("SPLASHSCREEN_APICLIENT", "news taken")
                    callback.response(true)
                }
            })
        }

    }
}