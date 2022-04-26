package com.example.mr_motor_.data.repository

import com.example.mr_motor_.data.storage.PostStorage
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.objects.PostType
import com.example.mr_motor_.domain.repository.NewsRepository

class NewsRepositoryImpl(private val postStorage: PostStorage) : NewsRepository {

    override fun loadNews(news: List<Post>?) {
        if(news != null){
            postStorage.savePostsArray(news, PostType.NEWS)
        }
    }

    override fun fetchNews() : List<Post> {
        return postStorage.fetchPostsList(PostType.NEWS)
    }
}