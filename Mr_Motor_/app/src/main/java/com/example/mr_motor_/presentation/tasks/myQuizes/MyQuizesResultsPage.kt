package com.example.mr_motor_.presentation.tasks.myQuizes

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
import com.example.mr_motor_.domain.models.QuizResultsCallback
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.usecase.GetQuizzesResultsUseCase
import com.example.mr_motor_.presentation.tasks.adapters.MyQuizResultAdapter

class MyQuizesResultsPage : AppCompatActivity(), QuizResultsCallback {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var adapter : MyQuizResultAdapter

    private val userStorage by lazy { UserSharedPrefStorage(context = this) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val getQuizzesResultsUseCase by lazy { GetQuizzesResultsUseCase(userRepository = userRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quiz_results)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = MyQuizResultAdapter(this)

        getQuizzesResultsUseCase.execute()

        recyclerView?.adapter = adapter
    }


    override fun response(result: List<QuizResultVO>?) {
        if(result != null){
            adapter.submitList(result)
        }
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, MyQuizesResultsPage::class.java)
            caller.startActivity(intent)
        }
    }

}