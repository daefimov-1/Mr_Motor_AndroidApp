package com.example.mr_motor_.presentation.tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        ApiClient.getApiService().get_short_quizes().enqueue(object : Callback<ShortQuizesResponse> {
            override fun onFailure(call: Call<ShortQuizesResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("QUIZ_APICLIENT", "doesn't work")
            }

            override fun onResponse(call: Call<ShortQuizesResponse>, response: Response<ShortQuizesResponse>) {
                adapter.submitList(response.body()?.quizzes)
            }
        })
        recyclerView?.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        // TODO: Use the ViewModel
    }

}