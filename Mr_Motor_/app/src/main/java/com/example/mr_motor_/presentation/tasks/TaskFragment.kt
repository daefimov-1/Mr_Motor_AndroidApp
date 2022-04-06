package com.example.mr_motor_.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Quiz


class TaskFragment : Fragment() {

    companion object {
        fun newInstance() = TaskFragment()
    }

    private lateinit var viewModel: TaskViewModel
    private var recyclerView : RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_quizPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = QuizListAdapter(context)
        val list : List<Quiz> = listOf(
            Quiz(1, "What car is yours?", R.drawable.sport_car),
            Quiz(2, "How many cars you need?", R.drawable.ic_racer)
        )
        adapter.submitList(list)
        recyclerView?.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}