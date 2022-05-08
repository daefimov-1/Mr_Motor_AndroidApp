package com.example.mr_motor_.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.PostStorage
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.data.models.PostResponse
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.repository.PostsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRepositoryImpl(
    private val postStorage: PostStorage,
    private val tokenStorage: TokenStorage
) : PostsRepository {

    override fun getPosts(postsListMutableLiveData: MutableLiveData<List<Post>>, postType: PostType) {
        when (postType) {
            PostType.NEWS -> postsListMutableLiveData.value = postStorage.fetchPostsList(PostType.NEWS)
            PostType.CAR -> postsListMutableLiveData.value = postStorage.fetchPostsList(PostType.CAR)
            PostType.COMPETITION -> postsListMutableLiveData.value =
                postStorage.fetchPostsList(PostType.COMPETITION)
            PostType.RACER -> postsListMutableLiveData.value = postStorage.fetchPostsList(PostType.RACER)
        }
    }

    override fun likePost(newsId: Long, resultLiveMutable: MutableLiveData<Boolean>) {
        ApiClient.getApiService()
            .like(newsId, tokenStorage.fetchAuthToken()).enqueue(object :
            Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val result = response.body().toString()
                resultLiveMutable.value = result == "Successfully Liked!"
            }
        })
    }

    override fun getLikedPosts(postsListMutableLiveData: MutableLiveData<List<Post>>) {
        ApiClient.getApiService().getLikedPosts(tokenStorage.fetchAuthToken()).enqueue(object :
            Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                postsListMutableLiveData.value = response.body()?.posts
            }
        })
    }

    override fun loadingPosts(resultLiveMutable: MutableLiveData<Boolean>, postType: PostType) {
        if (tokenStorage.fetchAuthToken() == "null") {
            when (postType) {
                PostType.NEWS -> {
                    ApiClient.getLongerConnectionApiService().getNews().enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.CAR -> {
                    ApiClient.getLongerConnectionApiService().getCars().enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.COMPETITION -> {
                    ApiClient.getLongerConnectionApiService().getCompetitions().enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.RACER -> {
                    ApiClient.getLongerConnectionApiService().getRacers().enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
            }

        } else {
            when (postType) {
                PostType.NEWS -> {
                    ApiClient.getLongerConnectionApiService()
                        .getNewsWithToken(tokenStorage.fetchAuthToken()).enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.CAR -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCarsWithToken(tokenStorage.fetchAuthToken()).enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.COMPETITION -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCompetitionsWithToken(tokenStorage.fetchAuthToken()).enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
                PostType.RACER -> {
                    ApiClient.getLongerConnectionApiService()
                        .getRacersWithToken(tokenStorage.fetchAuthToken()).enqueue(object :
                        Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            t.printStackTrace()
                            resultLiveMutable.value = false
                        }

                        override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                        ) {
                            postStorage.savePostsArray(response.body()?.posts, postType)
                            resultLiveMutable.value = true
                        }
                    })
                }
            }

        }
    }


}