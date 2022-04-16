package com.example.mr_motor_.presentation.tasks.myQuizes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.presentation.posts.FavouritePostsPage
import com.example.mr_motor_.presentation.tasks.adapters.QuizListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyQuizesPage : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var title: TextView? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quizzes)

        sessionManager = SessionManager(this)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val adapter = QuizListAdapter(this)
        ApiClient.getApiService().getMyQuizzes(sessionManager.fetchAuthToken()).enqueue(object :
            Callback<ShortQuizesResponse> {
            override fun onFailure(call: Call<ShortQuizesResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ShortQuizesResponse>, response: Response<ShortQuizesResponse>) {
                adapter.submitList(response.body()?.quizzes)
            }
        })
        recyclerView?.adapter = adapter
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, MyQuizesPage::class.java)
            caller.startActivity(intent)
        }
    }
}