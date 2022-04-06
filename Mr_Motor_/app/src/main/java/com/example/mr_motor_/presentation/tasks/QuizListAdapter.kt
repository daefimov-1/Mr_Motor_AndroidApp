package com.example.mr_motor_.presentation.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Quiz

class QuizListAdapter(context: Context?) : ListAdapter<Quiz, QuizViewHolder>(QuizDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.quizespage_viewholder, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}