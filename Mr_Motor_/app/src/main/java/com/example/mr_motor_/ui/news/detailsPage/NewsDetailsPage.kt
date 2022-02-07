package com.example.mr_motor_.ui.news.detailsPage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.mr_motor_.R
import com.example.mr_motor_.models.News

class NewsDetailsPage : AppCompatActivity() {

    private var news : News? = null
    private var title : TextView? = null
    private var text : TextView? = null
    private var star : ImageView?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.news_detailspage)

        supportActionBar?.hide()

        title = findViewById(R.id.tv_title)
        text = findViewById(R.id.tv_text)
        star = findViewById(R.id.iv_star)
        if (intent.hasExtra(OPEN_NEWS)){
            news = intent.getParcelableExtra(OPEN_NEWS)
            title?.setText(news?.title)
            text?.setText(news?.text)
            if(news?.favourite == true){
                star?.setImageResource(R.drawable.ic_star_favourite)
            }
            else{
                star?.setImageResource(R.drawable.ic_star)
            }

        }

    }

    companion object{
        private const val OPEN_NEWS : String = "NoteDetailsActivity.OPEN_NEWS"
        fun start(caller: Activity, news: News? ){
            val intent : Intent = Intent(caller, NewsDetailsPage::class.java)
            if(news != null){
                intent.putExtra(OPEN_NEWS, news)
            }
            caller.startActivity(intent)
        }
    }
}