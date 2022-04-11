package com.example.mr_motor_.presentation.posts.viewHolder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class CompetitionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.tv_competition_title)
    private val star : ImageView = itemView.findViewById(R.id.iv_competitionViewHolder_star)
    private val background : ImageView = itemView.findViewById(R.id.iv_competition_background)
    private var competition : Post? = null
    fun bind(competition: Post?){
        this.competition = competition
        title.text = competition?.title ?: "noting (fun bind NewsViewHolder)"
        star.setImageResource(R.drawable.ic_star_favourite)
        Picasso.with(itemView.context).load(competition?.thumbnail).into(background)

    }
    init {
        itemView.setOnClickListener {
            NewsDetailsPage.start(itemView.context as Activity, competition)
        }
    }
}