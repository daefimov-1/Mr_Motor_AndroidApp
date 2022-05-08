package com.example.mr_motor_.presentation.tasks.myQuizes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.presentation.tasks.adapters.QuizListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyQuizesPage : AppCompatActivity() {

    private lateinit var adapter: QuizListAdapter
    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null

    private val vm by viewModel<MyQuizzesPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quizzes)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = QuizListAdapter(this)

        vm.resultLive.observe(this, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })

        vm.getUserQuizzes()

        recyclerView?.adapter = adapter
    }

    companion object {
        fun start(caller: Activity) {
            val intent: Intent = Intent(caller, MyQuizesPage::class.java)
            caller.startActivity(intent)
        }
    }
}