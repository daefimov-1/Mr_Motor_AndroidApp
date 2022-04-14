package com.example.mr_motor_.presentation.tasks

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.presentation.tasks.fullQuiz.QuizDescriptionPage

class QuizViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title : TextView = itemView.findViewById(R.id.tv_quizViewHolder_title)
    private val image : ImageView = itemView.findViewById(R.id.iv_quizViewHolder_image)
    private var quiz : ShortQuizVO? = null
    fun bind(quiz: ShortQuizVO?){
        this.quiz = quiz
        title.text = quiz?.title ?: "nothing (fun bind NewsViewHolder)"
        image.setImageResource(R.drawable.sport_car) //R.drawable.ic_racer
    }
    init {
        itemView.setOnClickListener {
            QuizDescriptionPage.start(itemView.context as Activity, quiz)
        }
    }
}