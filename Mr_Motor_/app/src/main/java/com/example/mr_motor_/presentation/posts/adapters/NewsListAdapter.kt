package com.example.mr_motor_.presentation.posts.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.PostsDiffCallback
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class NewsListAdapter(context: Context?) : ListAdapter<Post, NewsListAdapter.NewsViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.newspage_viewholder, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val title : TextView = itemView.findViewById(R.id.tv_newsViewHolder_title)
        private val linq : TextView = itemView.findViewById(R.id.tv_newsViewHolder_linq)
        private val star : ImageView = itemView.findViewById(R.id.iv_newsViewHolder_star)
        private val background : ImageView = itemView.findViewById(R.id.iv_news_background)
        private lateinit var news : Post
        fun bind(news: Post){
            this.news = news
            title.text = news.title
            star.setImageResource(R.drawable.ic_star_favourite)
            linq.text = getSource(news.source.toString())
            Picasso.with(itemView.context).load(news.thumbnail).into(background)

            if(news.like){
                star.setImageResource(R.drawable.ic_star_favourite)
            }
            else{
                star.setImageResource(R.drawable.ic_star)
            }

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
}