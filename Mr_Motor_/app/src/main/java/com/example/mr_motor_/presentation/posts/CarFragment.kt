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
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.PostsCallback
import com.example.mr_motor_.domain.usecase.LoadCarsUseCase
import com.example.mr_motor_.presentation.posts.adapters.CarListAdapter

class CarFragment : Fragment(), PostsCallback {
    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private lateinit var adapter : CarListAdapter

    private val userStorage by lazy { UserSharedPrefStorage(context = requireContext()) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val loadCarsUseCase by lazy {
        LoadCarsUseCase(
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
        title?.text = getString(R.string.new_cars)

        loadCarsUseCase.execute()

        recyclerView = view.findViewById(R.id.rv_postsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = CarListAdapter(context)
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