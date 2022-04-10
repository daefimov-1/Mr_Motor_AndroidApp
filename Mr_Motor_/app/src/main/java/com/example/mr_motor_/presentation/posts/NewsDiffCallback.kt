package com.example.mr_motor_.presentation.posts

import androidx.recyclerview.widget.DiffUtil
import com.example.mr_motor_.domain.models.Post

class NewsDiffCallback : DiffUtil.ItemCallback<Post>()  {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.content == newItem.content
    }
}