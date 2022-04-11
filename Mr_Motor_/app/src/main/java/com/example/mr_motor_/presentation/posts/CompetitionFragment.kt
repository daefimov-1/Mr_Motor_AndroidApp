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
import com.example.mr_motor_.R
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.objects.PostType
import com.example.mr_motor_.presentation.posts.adapters.CompetitionListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompetitionFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.posts_fragment, container, false)

        title = view.findViewById(R.id.tv_posts)
        title?.text = getString(R.string.competitions)

        recyclerView = view.findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = CompetitionListAdapter(context)
        sessionManager = SessionManager(requireContext())

        ApiClient.getApiService().get_competitions().enqueue(object :
            Callback<PostResponse> {
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("COMPETITION_APICLIENT", "competitions cannot be taken")
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                adapter.submitList(response.body()?.posts)
            }
        })
        recyclerView?.adapter = adapter

        return view
    }
}