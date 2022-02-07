package com.example.mr_motor_.ui.news

import androidx.recyclerview.widget.DiffUtil
import com.example.mr_motor_.models.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>()  {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.text == newItem.text
    }
}