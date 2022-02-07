package com.example.mr_motor_.ui.news

import android.app.Activity
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.models.News
import com.example.mr_motor_.ui.news.detailsPage.NewsDetailsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.tv_newsViewHolder_title)
    private val linq : TextView = itemView.findViewById(R.id.tv_newsViewHolder_linq)
    private val star : ImageView = itemView.findViewById(R.id.iv_newsViewHolder_star)
    private var news : News? = null
    fun bind(news: News?){
        this.news = news
        title.text = news?.title ?: "noting (fun bind NewsViewHolder)"
        linq.text = news?.linq ?: "nothing"
        if(news?.favourite == true){
            star.setImageResource(R.drawable.ic_star_favourite)
        }
        else{
            star.setImageResource(R.drawable.ic_star)
        }
    }
    init {
        itemView.setOnClickListener {
            NewsDetailsPage.start(itemView.context as Activity, news)
        }
    }
}