package com.example.mr_motor_.presentation.tasks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.presentation.tasks.diffcallbacks.MyQuizDiffCallback
import com.example.mr_motor_.presentation.tasks.diffcallbacks.QuizDiffCallback
import com.example.mr_motor_.presentation.tasks.viewHolder.MyQuizResultViewHolder

class MyQuizResultAdapter(context: Context?) : ListAdapter<QuizResultVO, MyQuizResultViewHolder>(
    MyQuizDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuizResultViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.quizes_results_viewholder, parent, false)
        return MyQuizResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyQuizResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}