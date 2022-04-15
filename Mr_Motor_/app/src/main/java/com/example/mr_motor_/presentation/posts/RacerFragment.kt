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
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostResponse
import com.example.mr_motor_.domain.models.PostsCallback
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.usecase.LoadCompetitionsUseCase
import com.example.mr_motor_.domain.usecase.LoadRacersUseCase
import com.example.mr_motor_.presentation.posts.adapters.CompetitionListAdapter
import com.example.mr_motor_.presentation.posts.adapters.RacerListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RacerFragment : Fragment(), PostsCallback {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null

    private lateinit var adapter : RacerListAdapter

    private val userRepository by lazy { UserRepositoryImpl(context = requireContext()) }
    private val loadRacersUseCase by lazy {
        LoadRacersUseCase(
            userRepository = userRepository,
            callback = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.posts_fragment, container, false)

        title = view.findViewById(R.id.tv_posts)
        title?.text = getString(R.string.racers)

        loadRacersUseCase.execute()

        recyclerView = view.findViewById(R.id.rv_postsPage)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView?.layoutManager = staggeredGridLayoutManager

        adapter = RacerListAdapter(context)

        recyclerView?.adapter = adapter

        return view
    }

    override fun response(result: List<Post>?) {
        if(result != null){
            adapter.submitList(result)
        }else{
            Log.e("CARS_RESPONSE", "List<Post> == null")
        }

    }
}