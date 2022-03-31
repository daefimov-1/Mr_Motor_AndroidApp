package com.example.mr_motor_.ui

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.mr_motor_.MainActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.login.ApiClient
import com.example.mr_motor_.login.SessionManager
import com.example.mr_motor_.models.PostResponse
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashScreen : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        val gifImage: GifImageView = findViewById<GifImageView>(R.id.gif_image)
        gifImage.setImageResource(R.drawable.splashgif)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        playAudio()
        takeNews()
    }

    private fun playAudio(){
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_cars)
        mediaPlayer.start()
    }

    private fun takeNews(){
        apiClient.getApiService().get_news().enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("SPLASHSCREEN_APICLIENT", "news cannot be taken")
                NoInternetPage.start(this@SplashScreen)
                finish()
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                sessionManager.saveNewsArray(response.body()?.posts)
                Log.d("SPLASHSCREEN_APICLIENT", "news taken")
                MainActivity.start(this@SplashScreen)
                finish()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }
}