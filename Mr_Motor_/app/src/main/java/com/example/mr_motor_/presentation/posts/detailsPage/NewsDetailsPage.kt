package com.example.mr_motor_.presentation.posts.detailsPage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.squareup.picasso.Picasso

class NewsDetailsPage : AppCompatActivity() {

    private var news : Post? = null
    private var title : TextView? = null
    private var text : TextView? = null
    private var star : ImageView?  = null
    private var image : ImageView? = null
    private var buttonGoToSource : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.news_detailspage)

        supportActionBar?.hide()

        title = findViewById(R.id.tv_title)
        text = findViewById(R.id.tv_text)
        star = findViewById(R.id.iv_star)
        image = findViewById(R.id.iv_image_for_news)
        buttonGoToSource = findViewById(R.id.btn_go_to_source)
        if (intent.hasExtra(OPEN_NEWS)){
            news = intent.getParcelableExtra(OPEN_NEWS)
            title?.text = news?.title
            text?.text = news?.content
            star?.setImageResource(R.drawable.ic_star_favourite)
            Picasso.with(this).load(news?.thumbnail).into(image)

            buttonGoToSource?.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news?.source))
                startActivity(browserIntent)
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