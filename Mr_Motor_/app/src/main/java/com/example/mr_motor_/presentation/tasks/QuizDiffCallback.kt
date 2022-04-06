package com.example.mr_motor_.presentation.tasks

import androidx.recyclerview.widget.DiffUtil
import com.example.mr_motor_.domain.models.Quiz

class QuizDiffCallback : DiffUtil.ItemCallback<Quiz>()  {
    override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        return oldItem.title == newItem.title //Потом поменять потому что не по title надо сравнивать
    }
}