package com.example.mr_motor_.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mr_motor_.R
import org.apache.commons.io.IOUtils
import pl.droidsonroids.gif.GifImageView
import java.io.IOException
import java.io.InputStream

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val gifImage = findViewById<GifImageView>(R.id.gif_image)
        gifImage.setImageResource(R.drawable.loader)
    }
}