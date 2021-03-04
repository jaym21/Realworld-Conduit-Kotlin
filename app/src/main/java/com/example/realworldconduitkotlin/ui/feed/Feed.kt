package com.example.realworldconduitkotlin.ui.feed

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.realworldconduitkotlin.databinding.FragmentFeedBinding

class Feed : Fragment() {

     private var binding: FragmentFeedBinding? = null
    lateinit var viewModel: FeedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(context as Application)).get(FeedViewModel::class.java)

        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}