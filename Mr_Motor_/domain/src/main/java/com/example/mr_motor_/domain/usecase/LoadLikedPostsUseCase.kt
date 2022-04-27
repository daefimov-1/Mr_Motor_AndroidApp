package com.example.mr_motor_.domain.usecase

import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.PostsCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadLikedPostsUseCase(private val userRepository: UserRepository, private val callback: PostsCallback) {

    fun execute(){
        ApiClient.getApiService().getLikedPosts(userRepository.getAuthToken()).enqueue(object :
            Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                callback.response(result = response.body()?.posts)
            }
        })

    }
}