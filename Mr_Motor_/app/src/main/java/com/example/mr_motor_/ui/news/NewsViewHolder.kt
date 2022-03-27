package com.example.mr_motor_.ui.news

import android.app.Activity
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.models.Post
import com.example.mr_motor_.ui.news.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.tv_newsViewHolder_title)
    private val linq : TextView = itemView.findViewById(R.id.tv_newsViewHolder_linq)
    private val star : ImageView = itemView.findViewById(R.id.iv_newsViewHolder_star)
    private val background : ImageView = itemView.findViewById(R.id.iv_news_background)
    private var news : Post? = null
    fun bind(news: Post?){
        this.news = news
        title.text = news?.title ?: "noting (fun bind NewsViewHolder)"
        star.setImageResource(R.drawable.ic_star_favourite)
        linq.text = getSource(news?.thumbnail.toString())
        Picasso.with(itemView.context).load(news?.thumbnail).into(background)

//        if(news?.favourite == true){
//            star.setImageResource(R.drawable.ic_star_favourite)
//        }
//        else{
//            star.setImageResource(R.drawable.ic_star)
//        }
    }

    private fun getSource(str : String) : String {
        var indexOfPoint = str.lastIndexOf('.')
        var allThroughPoint = str.substring(0, indexOfPoint)
        indexOfPoint = allThroughPoint.lastIndexOf('.')
        allThroughPoint = allThroughPoint.substring(indexOfPoint)
        allThroughPoint = allThroughPoint.substring(0, allThroughPoint.indexOf('/'))

        return str.substring(0, indexOfPoint) + allThroughPoint
    }
    init {
        itemView.setOnClickListener {
            NewsDetailsPage.start(itemView.context as Activity, news)
        }
    }
}