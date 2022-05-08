package com.example.mr_motor_.presentation.posts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.presentation.posts.adapters.NewsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritePostsPage : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : NewsListAdapter

    private val vm by viewModel<PostsPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.news_fragment)

        title = findViewById(R.id.tv_news)
        title.text = getString(R.string.favourite_posts)
        recyclerView = findViewById(R.id.rv_newsPage)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(this)

        vm.postsListLiveData.observe(this, Observer {
            if(it != null){
                adapter.submitList(it)
            }else{
                Log.e("LIKED_POSTS", "List<Post> == null")
            }
        })

        vm.getLikedPosts()

        recyclerView.adapter = adapter
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, FavouritePostsPage::class.java)
            caller.startActivity(intent)
        }
    }

}