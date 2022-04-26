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

class CompetitionListAdapter(context: Context?) : ListAdapter<Post, CompetitionListAdapter.CompetitionViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.competitionpage_viewholder, parent, false)
        return CompetitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CompetitionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val title : TextView = itemView.findViewById(R.id.tv_competition_title)
        private val star : ImageView = itemView.findViewById(R.id.iv_competitionViewHolder_star)
        private val background : ImageView = itemView.findViewById(R.id.iv_competition_background)
        private lateinit var competition : Post
        fun bind(competition: Post){
            this.competition = competition
            title.text = competition.title ?: "noting (fun bind NewsViewHolder)"
            Picasso.with(itemView.context).load(competition.thumbnail).into(background)

            if(competition.like){
                star.setImageResource(R.drawable.ic_star_favourite)
            }
            else{
                star.setImageResource(R.drawable.ic_star)
            }
        }
        init {
            itemView.setOnClickListener {
                NewsDetailsPage.start(itemView.context as Activity, competition)
            }
        }
    }
}