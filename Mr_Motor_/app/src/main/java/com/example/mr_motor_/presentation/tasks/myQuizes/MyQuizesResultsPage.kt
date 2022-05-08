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
import com.example.mr_motor_.presentation.tasks.adapters.MyQuizResultAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyQuizesResultsPage : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var adapter: MyQuizResultAdapter

    private val vm by viewModel<MyQuizzesResultPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quiz_results)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = MyQuizResultAdapter(this)

        vm.resultLive.observe(this, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })

        vm.getAllResults()

        recyclerView?.adapter = adapter
    }

    companion object {
        fun start(caller: Activity) {
            val intent: Intent = Intent(caller, MyQuizesResultsPage::class.java)
            caller.startActivity(intent)
        }
    }

}