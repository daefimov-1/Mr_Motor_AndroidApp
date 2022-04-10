package com.example.mr_motor_.presentation.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private var recyclerView: RecyclerView? = null
    private var list: List<Post>? = listOf()

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_newsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(context)
        sessionManager = SessionManager(requireContext())
        adapter.submitList(sessionManager.fetchNewsList())
        recyclerView?.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

    }

}