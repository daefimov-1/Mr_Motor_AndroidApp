package com.example.mr_motor_.presentation.posts.detailsPage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailsPage : AppCompatActivity() {

    private lateinit var news : Post
    private var title : TextView? = null
    private var text : TextView? = null
    private var star : ImageView?  = null
    private var image : ImageView? = null
    private var buttonGoToSource : Button? = null

    private val vm by viewModel<NewsDetailsPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.news_detailspage)

        supportActionBar?.hide()

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

            vm.resultLive.observe(this, Observer {
                if(it){
                    star?.setImageResource(R.drawable.ic_star_favourite)
                }
                else{
                    star?.setImageResource(R.drawable.ic_star)
                }
            })

            star?.setOnClickListener {
                vm.like(news.id)
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