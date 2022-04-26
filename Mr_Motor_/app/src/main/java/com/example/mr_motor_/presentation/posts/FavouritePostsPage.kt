package com.example.mr_motor_.presentation.posts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.presentation.posts.adapters.NewsListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouritePostsPage : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.news_fragment)

        sessionManager = SessionManager(this)

        title = findViewById(R.id.tv_news)
        title.text = getString(R.string.favourite_posts)
        recyclerView = findViewById(R.id.rv_newsPage)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NewsListAdapter(this)
        ApiClient.getApiService().getLikedPosts(sessionManager.fetchAuthToken()).enqueue(object :
            Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                adapter.submitList(response.body()?.posts)
            }
        })

        recyclerView.adapter = adapter
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, FavouritePostsPage::class.java)
            caller.startActivity(intent)
        }
    }
}