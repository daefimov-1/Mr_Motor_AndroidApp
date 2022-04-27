package com.example.mr_motor_.presentation.posts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostsCallback
import com.example.mr_motor_.domain.usecase.LoadLikedPostsUseCase
import com.example.mr_motor_.presentation.posts.adapters.NewsListAdapter

class FavouritePostsPage : AppCompatActivity(), PostsCallback {

    private lateinit var title : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : NewsListAdapter

    private val userStorage by lazy { UserSharedPrefStorage(context = this) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val loadLikedPostsUseCase by lazy { LoadLikedPostsUseCase(userRepository = userRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.news_fragment)

        title = findViewById(R.id.tv_news)
        title.text = getString(R.string.favourite_posts)
        recyclerView = findViewById(R.id.rv_newsPage)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(this)

        loadLikedPostsUseCase.execute()

        recyclerView.adapter = adapter
    }

    override fun response(result: List<Post>?) {
        if(result != null){
            adapter.submitList(result)
        }
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, FavouritePostsPage::class.java)
            caller.startActivity(intent)
        }
    }

}