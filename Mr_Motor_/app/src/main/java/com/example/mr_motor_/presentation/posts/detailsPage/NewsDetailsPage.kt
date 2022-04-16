package com.example.mr_motor_.presentation.posts.detailsPage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.LoginResponse
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.login.ApiClient
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDetailsPage : AppCompatActivity() {

    private lateinit var news : Post
    private var title : TextView? = null
    private var text : TextView? = null
    private var star : ImageView?  = null
    private var image : ImageView? = null
    private var buttonGoToSource : Button? = null

    private lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.news_detailspage)

        supportActionBar?.hide()

        sessionManager = SessionManager(this)

        title = findViewById(R.id.tv_title)
        text = findViewById(R.id.tv_text)
        image = findViewById(R.id.iv_image_for_news)
        buttonGoToSource = findViewById(R.id.btn_go_to_source)
        star = findViewById(R.id.ib_star)
        if (intent.hasExtra(OPEN_NEWS)){
            news = intent.getParcelableExtra(OPEN_NEWS)!!
            title?.text = news.title
            text?.text = news.content

            if(news.like){
                star?.setImageResource(R.drawable.ic_star_favourite)
            }else{
                star?.setImageResource(R.drawable.ic_star)
            }

            Picasso.with(this).load(news.thumbnail).into(image)

            buttonGoToSource?.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.source))
                startActivity(browserIntent)
            }

            star?.setOnClickListener {
                ApiClient.getApiService().like(news.id, sessionManager.fetchAuthToken()).enqueue(object :
                    Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("LIKE", "failed")
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        val result = response.body().toString()

                        val toast = Toast.makeText(this@NewsDetailsPage, result, Toast.LENGTH_LONG)

                        if(result == "Successfully Liked!"){
                            star?.setImageResource(R.drawable.ic_star_favourite)
                        }
                        else{
                            star?.setImageResource(R.drawable.ic_star)
                        }
                        toast.show()
                    }
                })
            }

        }

    }

    companion object{
        private const val OPEN_NEWS : String = "NewsDetailsActivity.OPEN_NEWS"
        fun start(caller: Activity, news: Post? ){
            val intent : Intent = Intent(caller, NewsDetailsPage::class.java)
            if(news != null){
                intent.putExtra(OPEN_NEWS, news)
            }
            caller.startActivity(intent)
        }
    }
}