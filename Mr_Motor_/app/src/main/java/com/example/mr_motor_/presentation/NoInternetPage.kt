package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mr_motor_.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoInternetPage : AppCompatActivity() {

    private lateinit var button : ImageButton

    private val vm by viewModel<StartAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet_page)

        supportActionBar?.hide()

        button = findViewById(R.id.ib_refresh)

        vm.resultLive.observe(this, Observer {
            if(it){
                MainActivity.start(this@NoInternetPage)
                finish()
            }
            else{
                val toast : Toast = Toast.makeText(this@NoInternetPage, "No internet connection", Toast.LENGTH_LONG)
                toast.show()
                button.isClickable = true
            }
        })

        button.setOnClickListener {
            it.isClickable = false
            vm.loadAllPosts()
        }

    }

    companion object{
        fun start(caller : Activity){
            val intent : Intent = Intent(caller, NoInternetPage::class.java)
            caller.startActivity(intent)
        }
    }
}