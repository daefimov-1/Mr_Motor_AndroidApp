package com.example.mr_motor_.presentation.tasks.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO

class MyQuizDiffCallback : DiffUtil.ItemCallback<QuizResultVO>()  {
    override fun areItemsTheSame(oldItem: QuizResultVO, newItem: QuizResultVO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QuizResultVO, newItem: QuizResultVO): Boolean {
        return oldItem.quiz.description == newItem.quiz.description
    }

}