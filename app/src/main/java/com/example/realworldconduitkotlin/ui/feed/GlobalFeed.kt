package com.example.realworldconduitkotlin.ui.feed


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.databinding.FragmentGlobalFeedBinding

class GlobalFeed : Fragment() {

    private var binding: FragmentGlobalFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentGlobalFeedBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        //initializing adapter and recyclerView
        feedAdapter = ArticleFeedRVAdapter()
        binding?.rvGlobalFeed?.layoutManager = LinearLayoutManager(context)
        binding?.rvGlobalFeed?.adapter = feedAdapter

        
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fetching the feed into recyclerView
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}