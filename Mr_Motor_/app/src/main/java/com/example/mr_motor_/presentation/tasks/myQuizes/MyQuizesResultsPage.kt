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
import com.example.mr_motor_.domain.models.QuizResultResponse
import com.example.mr_motor_.domain.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.presentation.posts.adapters.CarListAdapter
import com.example.mr_motor_.presentation.tasks.adapters.MyQuizResultAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyQuizesResultsPage : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quiz_results)

        sessionManager = SessionManager(this)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val adapter = MyQuizResultAdapter(this)
        ApiClient.getApiService().getQuizesResults(sessionManager.fetchAuthToken()).enqueue(object :
            Callback<QuizResultResponse> {
            override fun onFailure(call: Call<QuizResultResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("QUIZ_APICLIENT", "doesn't work")
            }

            override fun onResponse(call: Call<QuizResultResponse>, response: Response<QuizResultResponse>) {
                adapter.submitList(response.body()?.quizResults)
            }
        })
        recyclerView?.adapter = adapter
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, MyQuizesResultsPage::class.java)
            caller.startActivity(intent)
        }
    }
}