package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.NewsRepositoryImpl
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.PostSharedPrefStorage
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.usecase.LoadNewsUseCase

class NoInternetPage : AppCompatActivity(), ResponseCallback {

    private lateinit var button : ImageButton

    private val userStorage by lazy { UserSharedPrefStorage(context = applicationContext) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }

    private val postStorage by lazy { PostSharedPrefStorage(context = applicationContext) }
    private val newsRepository by lazy { NewsRepositoryImpl(postStorage = postStorage) }

    private val loadNewsUseCase by lazy {
        LoadNewsUseCase(
            newsRepository = newsRepository,
            userRepository = userRepository,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet_page)

        supportActionBar?.hide()

        button = findViewById(R.id.ib_refresh)

        button.setOnClickListener {
            it.isClickable = false
            loadNewsUseCase.execute()
        }

    }

    companion object{
        fun start(caller : Activity){
            val intent : Intent = Intent(caller, NoInternetPage::class.java)
            caller.startActivity(intent)
        }
    }

    override fun response(result: Boolean) {
        if(result){
            MainActivity.start(this@NoInternetPage)
            finish()
        }
        else{
            val toast : Toast = Toast.makeText(this@NoInternetPage, "No internet connection", Toast.LENGTH_LONG)
            toast.show()
            button.isClickable = true
        }
    }
}