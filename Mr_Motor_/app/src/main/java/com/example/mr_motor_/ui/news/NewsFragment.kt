package com.example.mr_motor_.ui.news

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
import com.example.mr_motor_.login.ApiClient
import com.example.mr_motor_.models.Post
import com.example.mr_motor_.models.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private var recyclerView : RecyclerView? = null
    private lateinit var apiClient: ApiClient
    private var list : List<Post>? = listOf()

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        apiClient = ApiClient()
        recyclerView = view.findViewById(R.id.rv_newsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(context)
        apiClient.getApiService().get_news().enqueue(object : Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("NEWSFRAGMENT_APICLIENT", "news cannot be taken")
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                list = response.body()?.posts
                adapter.submitList(list)
                Log.d("NEWSFRAGMENT_APICLIENT", "news taken")
            }
        })
            
        recyclerView?.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

    }

}