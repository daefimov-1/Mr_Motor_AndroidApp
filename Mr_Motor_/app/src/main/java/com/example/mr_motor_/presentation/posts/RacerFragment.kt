package com.example.mr_motor_.presentation.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.adapters.RacerListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RacerFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null

    private lateinit var adapter : RacerListAdapter

    private val vm by viewModel<PostsPageViewModel>()

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

        adapter = RacerListAdapter(context)

        vm.postsListLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null){
                adapter.submitList(it)
            }else{
                Log.e("RACERS", "List<Post> == null")
            }
        })

        vm.getRacers()

        recyclerView?.adapter = adapter

        return view
    }

}