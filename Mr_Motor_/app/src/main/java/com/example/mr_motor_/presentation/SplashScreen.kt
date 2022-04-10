package com.example.mr_motor_.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.NewsRepositoryImpl
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.usecase.LoadNewsUseCase
import com.example.mr_motor_.domain.usecase.LoginUseCase
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashScreen : AppCompatActivity(), ResponseCallback {

    private lateinit var mediaPlayer: MediaPlayer

    private val newsRepository by lazy { NewsRepositoryImpl(context = this) }
    private val loadNewsUseCase by lazy { LoadNewsUseCase(newsRepository = newsRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        val gifImage: GifImageView = findViewById<GifImageView>(R.id.gif_image)
        gifImage.setImageResource(R.drawable.splashgif)

        playAudio()
        loadNewsUseCase.execute()
    }

    private fun playAudio(){
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_cars)
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    override fun response(result: Boolean) {
        if(result){
            MainActivity.start(this@SplashScreen)
            finish()
        }
        else{
            NoInternetPage.start(this@SplashScreen)
            finish()
        }
    }
}