package com.example.mr_motor_.ui.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.models.News

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private var recyclerView : RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        recyclerView = view.findViewById(R.id.rv_newsPage)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val adapter = NewsListAdapter(context)
        val list : List<News> = listOf(News(1, "Lucid Air - challenge for Tesla", "Lorem, ipsum dolor sit amet consectetur adipisicing, elit. Nobis, dolores, nemo. Quas dicta temporibus voluptatibus nostrum debitis ex eligendi, libero inventore, totam tempore ipsam est excepturi deserunt laborum distinctio voluptas eius delectus, natus! Libero omnis magni vero voluptates suscipit illum earum magnam minima, veritatis perferendis dolore consectetur eius minus voluptate?",
            1, "carwow.uk", true))
        adapter.submitList(list)
        recyclerView?.adapter = adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}