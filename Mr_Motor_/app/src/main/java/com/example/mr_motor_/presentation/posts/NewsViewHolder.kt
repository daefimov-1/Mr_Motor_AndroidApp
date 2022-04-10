package com.example.mr_motor_.presentation.posts

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
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
        linq.text = getSource(news?.source.toString())
        Picasso.with(itemView.context).load(news?.thumbnail).into(background)

//        if(posts?.favourite == true){
//            star.setImageResource(R.drawable.ic_star_favourite)
//        }
//        else{
//            star.setImageResource(R.drawable.ic_star)
//        }
    }

    private fun getSource(str : String) : String {


        return str.split("/")[2]
    }
    init {
        itemView.setOnClickListener {
            NewsDetailsPage.start(itemView.context as Activity, news)
        }
    }
}