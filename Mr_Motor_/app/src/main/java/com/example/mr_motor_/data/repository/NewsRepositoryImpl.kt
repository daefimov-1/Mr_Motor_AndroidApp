package com.example.mr_motor_.data.repository

import android.content.Context
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.objects.PostType
import com.example.mr_motor_.domain.repository.NewsRepository

class NewsRepositoryImpl(context: Context) : NewsRepository {

    val sessionManager: SessionManager = SessionManager(context)

    override fun loadNews(news: List<Post>?) {
        if(news != null){
            sessionManager.savePostsArray(news, PostType.NEWS)
        }
    }

    override fun fetchNews() : List<Post> {
        return sessionManager.fetchPostsList(PostType.NEWS)
    }
}