package com.example.mr_motor_.presentation.tasks.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO

class QuizDiffCallback : DiffUtil.ItemCallback<ShortQuizVO>()  {
    override fun areItemsTheSame(oldItem: ShortQuizVO, newItem: ShortQuizVO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShortQuizVO, newItem: ShortQuizVO): Boolean {
        return oldItem.description == newItem.description
    }

}