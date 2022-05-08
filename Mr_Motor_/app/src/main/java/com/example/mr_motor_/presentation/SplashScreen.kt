package com.example.mr_motor_.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mr_motor_.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.droidsonroids.gif.GifImageView


class SplashScreen : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    private val vm by viewModel<StartAppViewModel>()
    private var loadingCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        val gifImage: GifImageView = findViewById<GifImageView>(R.id.gif_image)
        gifImage.setImageResource(R.drawable.splashgif)

        vm.resultLive.observe(this, Observer {
            if (it) {
                loadingCount++
                Toast.makeText(this@SplashScreen, "$loadingCount loading is successful", Toast.LENGTH_LONG).show()
            } else {
                NoInternetPage.start(this@SplashScreen)
                finish()
            }
        })

        vm.lastResultLive.observe(this, Observer {
            if (it) {
                MainActivity.start(this@SplashScreen)
                finish()
            } else {
                NoInternetPage.start(this@SplashScreen)
                finish()
            }
        })

        playAudio()
        vm.loadAllPosts()
    }

    private fun playAudio() {
        mediaPlayer = MediaPlayer.create(this, R.raw.audio_cars)
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

}