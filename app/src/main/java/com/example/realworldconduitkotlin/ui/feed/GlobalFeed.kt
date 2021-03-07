package com.example.realworldconduitkotlin.ui.feed


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.databinding.FragmentGlobalFeedBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel
import com.example.realworldconduitkotlin.ui.auth.SignIn

class GlobalFeed : Fragment() {

    private var binding: FragmentGlobalFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter
    private val authViewModel: AuthViewModel by activityViewModels()
    private var isLoggedIn: Boolean = false

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



    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}