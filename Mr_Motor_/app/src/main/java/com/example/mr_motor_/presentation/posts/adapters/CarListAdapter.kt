package com.example.mr_motor_.presentation.posts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.PostsDiffCallback
import com.example.mr_motor_.presentation.posts.viewHolder.CarViewHolder
import com.example.mr_motor_.presentation.posts.viewHolder.CompetitionViewHolder

class CarListAdapter(context: Context?) : ListAdapter<Post, CarViewHolder>(PostsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehiclespage_viewholder, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}