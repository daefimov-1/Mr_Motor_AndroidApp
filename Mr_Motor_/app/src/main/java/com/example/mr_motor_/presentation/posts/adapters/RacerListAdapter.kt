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

class RacerListAdapter(context: Context?) : ListAdapter<Post, RacerListAdapter.RacerViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.racerpage_viewholder, parent, false)
        return RacerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RacerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RacerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val firstName : TextView = itemView.findViewById(R.id.tv_racer_firstName)
        private val secondName : TextView = itemView.findViewById(R.id.tv_racer_secondName)
        private val star : ImageView = itemView.findViewById(R.id.iv_racerViewHolder_star)
        private val background : ImageView = itemView.findViewById(R.id.iv_racer_background)
        private lateinit var racer : Post
        fun bind(racer: Post){
            this.racer = racer
            val arrOfName = racer.title.split(' ')
            firstName.text = arrOfName.get(0) ?: "NULL"
            secondName.text = arrOfName.get(1) ?: "NULL"
            star.setImageResource(R.drawable.ic_star_favourite)
            Picasso.with(itemView.context).load(racer.thumbnail).into(background)

            if(racer.like){
                star.setImageResource(R.drawable.ic_star_favourite)
            }
            else{
                star.setImageResource(R.drawable.ic_star)
            }
        }

        init {
            itemView.setOnClickListener {
                NewsDetailsPage.start(itemView.context as Activity, racer)
            }
        }
    }
}