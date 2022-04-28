package com.example.mr_motor_.presentation.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.NewsRepositoryImpl
import com.example.mr_motor_.data.storage.PostSharedPrefStorage
import com.example.mr_motor_.presentation.posts.adapters.NewsListAdapter

class NewsFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val postStorage by lazy { PostSharedPrefStorage(context = requireContext()) }
    private val newsRepository by lazy { NewsRepositoryImpl(postStorage = postStorage) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_newsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(context)
        adapter.submitList(newsRepository.fetchNews())
        recyclerView?.adapter = adapter

        return view
    }



}