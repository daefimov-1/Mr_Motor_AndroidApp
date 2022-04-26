package com.example.mr_motor_.domain.repository

import com.example.mr_motor_.domain.models.Post

interface NewsRepository {
    fun loadNews(news: List<Post>?)
    fun fetchNews() : List<Post>
}