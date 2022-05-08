package com.example.mr_motor_.presentation.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.presentation.posts.adapters.NewsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val vm by viewModel<PostsPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_newsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(context)

        vm.postsListLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null){
                adapter.submitList(it)
            }else{
                Log.e("NEWS", "List<Post> == null")
            }
        })

        vm.getNews()

        recyclerView?.adapter = adapter

        return view
    }



}