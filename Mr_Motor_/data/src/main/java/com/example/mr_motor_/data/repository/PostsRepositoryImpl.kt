package com.example.mr_motor_.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.PostStorage
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.data.models.PostResponse
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.repository.PostsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PostsRepositoryImpl(
    private val postStorage: PostStorage,
    private val tokenStorage: TokenStorage
) : PostsRepository {

    override fun getPosts(
        postsListMutableLiveData: MutableLiveData<List<Post>>,
        postType: PostType
    ) {
        when (postType) {
            PostType.NEWS -> postsListMutableLiveData.value =
                postStorage.fetchPostsList(PostType.NEWS)
            PostType.CAR -> postsListMutableLiveData.value =
                postStorage.fetchPostsList(PostType.CAR)
            PostType.COMPETITION -> postsListMutableLiveData.value =
                postStorage.fetchPostsList(PostType.COMPETITION)
            PostType.RACER -> postsListMutableLiveData.value =
                postStorage.fetchPostsList(PostType.RACER)
        }
    }

    override fun likePost(newsId: Long, resultLiveMutable: MutableLiveData<Boolean>) {
        ApiClient.getApiService()
            .like(newsId, tokenStorage.fetchAuthToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: String?) {
                    resultLiveMutable.value = t == "Successfully Liked!"
                }

                override fun onError(e: Throwable?) {
                    Log.e("POSTS_REPO", e.toString())
                    resultLiveMutable.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun getLikedPosts(postsListMutableLiveData: MutableLiveData<List<Post>>) {
        ApiClient.getApiService()
            .getLikedPosts(tokenStorage.fetchAuthToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<PostResponse> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: PostResponse?) {
                    postsListMutableLiveData.value = t?.posts
                }

                override fun onError(e: Throwable?) {
                    Log.e("POSTS_REPO", e.toString())
                    postsListMutableLiveData.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun loadingPosts(resultLiveMutable: MutableLiveData<Boolean>, postType: PostType) {
        if (tokenStorage.fetchAuthToken() == "null") {
            when (postType) {
                PostType.NEWS -> {
                    ApiClient.getLongerConnectionApiService()
                        .getNews()
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.CAR -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCars()
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.COMPETITION -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCompetitions()
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.RACER -> {
                    ApiClient.getLongerConnectionApiService()
                        .getRacers()
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
            }

        } else {
            when (postType) {
                PostType.NEWS -> {
                    ApiClient.getLongerConnectionApiService()
                        .getNewsWithToken(tokenStorage.fetchAuthToken())
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.CAR -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCarsWithToken(tokenStorage.fetchAuthToken())
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.COMPETITION -> {
                    ApiClient.getLongerConnectionApiService()
                        .getCompetitionsWithToken(tokenStorage.fetchAuthToken())
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
                PostType.RACER -> {
                    ApiClient.getLongerConnectionApiService()
                        .getRacersWithToken(tokenStorage.fetchAuthToken())
                        .observeOn(Schedulers.io())
                        .doOnNext { postResponse ->
                            postStorage.savePostsArray(
                                postResponse.posts,
                                postType
                            )
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<PostResponse> {
                            override fun onNext(t: PostResponse?) {
                                resultLiveMutable.value = true
                            }

                            override fun onSubscribe(d: Disposable?) {
                            }

                            override fun onError(e: Throwable?) {
                                Log.e("POSTS_REPO", e.toString())
                                resultLiveMutable.value = false
                            }

                            override fun onComplete() {
                            }
                        })
                }
            }

        }
    }


}