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
import com.example.mr_motor_.presentation.posts.viewHolder.RacerViewHolder

class RacerListAdapter(context: Context?) : ListAdapter<Post, RacerViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.racerpage_viewholder, parent, false)
        return RacerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RacerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}