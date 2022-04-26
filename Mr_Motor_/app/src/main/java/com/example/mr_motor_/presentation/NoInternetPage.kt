package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.objects.PostType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoInternetPage : AppCompatActivity() {

    private lateinit var button : ImageButton

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet_page)

        supportActionBar?.hide()

        button = findViewById(R.id.ib_refresh)

        sessionManager = SessionManager(this)

        button.setOnClickListener {
            it.isClickable = false
            tryTakeNews()
        }

    }

    private fun tryTakeNews(){
        ApiClient.getApiService().get_news().enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("NOINTERNET_APICLIENT", "news cannot be taken")
                val toast : Toast = Toast.makeText(this@NoInternetPage, "No internet connection", Toast.LENGTH_LONG)
                toast.show()
                button.isClickable = true
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                sessionManager.savePostsArray(response.body()?.posts, PostType.NEWS)
                Log.d("NOINTERNET_APICLIENT", "news taken")
                MainActivity.start(this@NoInternetPage)
                finish()
            }
        })
    }

    companion object{
        fun start(caller : Activity){
            val intent : Intent = Intent(caller, NoInternetPage::class.java)
            caller.startActivity(intent)
        }
    }
}