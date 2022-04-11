package com.example.mr_motor_.presentation.posts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.PostsDiffCallback
import com.example.mr_motor_.presentation.posts.viewHolder.CompetitionViewHolder
import com.example.mr_motor_.presentation.posts.viewHolder.NewsViewHolder

class CompetitionListAdapter(context: Context?) : ListAdapter<Post, CompetitionViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.competitionpage_viewholder, parent, false)
        return CompetitionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}