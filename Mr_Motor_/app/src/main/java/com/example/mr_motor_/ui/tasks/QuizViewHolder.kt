package com.example.mr_motor_.ui.tasks

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.models.News
import com.example.mr_motor_.models.Quiz

class QuizViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.tv_quizViewHolder_title)
    private val image : ImageView = itemView.findViewById(R.id.iv_quizViewHolder_image)
    private var quiz : Quiz? = null
    fun bind(quiz: Quiz?){
        this.quiz = quiz
        title.text = quiz?.title ?: "noting (fun bind NewsViewHolder)"
        image.setImageResource(quiz?.image!!)

    }
}