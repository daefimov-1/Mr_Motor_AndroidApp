package com.example.mr_motor_.domain.usecase

import android.util.Log
import android.widget.Toast
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.NewsRepository
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeUseCase(private val userRepository: UserRepository, private val callback: ResponseCallback) {

    fun execute(newsId : Long){

        ApiClient.getApiService().like(newsId, userRepository.getAuthToken()).enqueue(object :
            Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
                Log.e("LIKE", "failed")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val result = response.body().toString()

                if(result == "Successfully Liked!"){
                    callback.response(true)
                }
                else{
                    callback.response(false)
                }
            }
        })


    }
}