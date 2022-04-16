package com.example.mr_motor_.presentation.tasks.viewHolder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.presentation.tasks.fullQuiz.QuizDescriptionPage
import kotlin.math.roundToInt

class MyQuizResultViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val name : TextView = itemView.findViewById(R.id.tv_quiz_name)
    private val resultNumbers : TextView = itemView.findViewById(R.id.tv_result_in_numbers)
    private val percentage_text : TextView = itemView.findViewById(R.id.tv_percentage)
    private val greenStrokeCl : ConstraintLayout = itemView.findViewById(R.id.cl_inner_constraint)
    private lateinit var result : QuizResultVO
    fun bind(result: QuizResultVO){
        this.result = result
        result.achieved
        var percentage = ((result.achieved.toFloat() / result.amount) * 100).roundToInt()
        percentage_text.text = "$percentage%"
        resultNumbers.text = "${result.achieved}/${result.amount}"
        name.text = result.quiz.title

        (greenStrokeCl.layoutParams as ConstraintLayout.LayoutParams)
            .matchConstraintPercentWidth = percentage.toFloat() /100
        greenStrokeCl.requestLayout()

    }
}