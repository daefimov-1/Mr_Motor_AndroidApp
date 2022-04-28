package com.example.mr_motor_.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.ShortQuizzesCallback
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.usecase.LoadQuizzesUseCase
import com.example.mr_motor_.presentation.tasks.adapters.QuizListAdapter

class TaskFragment : Fragment(), ShortQuizzesCallback {

    private var recyclerView : RecyclerView? = null
    private lateinit var adapter : QuizListAdapter

    private val loadQuizzesUseCase = LoadQuizzesUseCase(callback = this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_quizPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = QuizListAdapter(context)

        loadQuizzesUseCase.execute()

        recyclerView?.adapter = adapter

        return view
    }

    override fun response(result: List<ShortQuizVO>?) {
        if(result != null){
            adapter.submitList(result)
        }
    }




}