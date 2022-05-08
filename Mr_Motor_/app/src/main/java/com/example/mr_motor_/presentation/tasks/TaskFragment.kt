package com.example.mr_motor_.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.presentation.tasks.adapters.QuizListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskFragment : Fragment() {

    private var recyclerView : RecyclerView? = null
    private lateinit var adapter : QuizListAdapter

    private val vm by viewModel<TaskFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_quizPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = QuizListAdapter(context)

        vm.getQuizzes()

        vm.resultLive.observe(viewLifecycleOwner, Observer {
            if(it != null){
                adapter.submitList(it)
            }
        })

        recyclerView?.adapter = adapter

        return view
    }

}