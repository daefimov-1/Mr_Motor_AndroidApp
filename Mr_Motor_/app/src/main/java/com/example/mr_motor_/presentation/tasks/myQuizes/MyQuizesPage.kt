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
import com.example.mr_motor_.domain.models.ShortQuizzesCallback
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.usecase.GetUserQuizzesUseCase
import com.example.mr_motor_.presentation.tasks.adapters.QuizListAdapter

class MyQuizesPage : AppCompatActivity(), ShortQuizzesCallback {

    private lateinit var adapter : QuizListAdapter
    private var recyclerView : RecyclerView? = null
    private var title: TextView? = null

    private val userStorage by lazy { UserSharedPrefStorage(context = applicationContext) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val getUserQuizzesUseCase by lazy { GetUserQuizzesUseCase(userRepository = userRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.posts_fragment)

        title = findViewById(R.id.tv_posts)
        title?.text = getString(R.string.my_quizzes)

        recyclerView = findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = QuizListAdapter(this)

        getUserQuizzesUseCase.execute()

        recyclerView?.adapter = adapter
    }

    override fun response(result: List<ShortQuizVO>?) {
        if(result != null){
            adapter.submitList(result)
        }
    }

    companion object{
        fun start(caller: Activity){
            val intent : Intent = Intent(caller, MyQuizesPage::class.java)
            caller.startActivity(intent)
        }
    }
}