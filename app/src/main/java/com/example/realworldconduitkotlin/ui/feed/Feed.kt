package com.example.realworldconduitkotlin.ui.feed

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.api.models.entities.User
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.databinding.FragmentFeedBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel
import com.example.realworldconduitkotlin.ui.auth.SignIn

class Feed : Fragment() {

    private var binding: FragmentFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter
    private val authViewModel: AuthViewModel by activityViewModels()
    private var isLoggedIn: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        val user = authViewModel.user.value
        updateLogInState(user)

        //initializing adapter and recyclerView
        feedAdapter = ArticleFeedRVAdapter()
        binding?.rvFeed?.layoutManager = LinearLayoutManager(context)
        binding?.rvFeed?.adapter = feedAdapter

        //fetching the feed into recyclerView
        viewModel.fetchFeed()
        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }

        binding?.ivUserAvatar?.setOnClickListener {
            if (!isLoggedIn) {
                val loginIntent = Intent(context, SignIn::class.java)
                startActivity(loginIntent)
            }
        }

        
        return binding!!.root
    }


    private fun updateLogInState(user: User?) {
        when(user) {
            //user is logged in
            is User -> {
                Glide.with(this).load(user.image).into(binding?.ivUserAvatar!!)
                isLoggedIn = true
            }
            //user is not logged in
            else -> {
                isLoggedIn = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}