package com.example.mr_motor_.presentation.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.presentation.posts.adapters.RacerListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RacerFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.posts_fragment, container, false)

        title = view.findViewById(R.id.tv_posts)
        title?.text = getString(R.string.racers)

        recyclerView = view.findViewById(R.id.rv_postsPage)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView?.layoutManager = staggeredGridLayoutManager

        val adapter = RacerListAdapter(context)
        sessionManager = SessionManager(requireContext())

        ApiClient.getLongerConnectionApiService().get_racers().enqueue(object :
            Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("RACER_APICLIENT", "racers cannot be taken")
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                adapter.submitList(response.body()?.posts)
            }
        })
        recyclerView?.adapter = adapter

        return view
    }
}