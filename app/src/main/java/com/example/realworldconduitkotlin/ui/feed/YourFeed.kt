package com.example.realworldconduitkotlin.ui.feed

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.api.models.entities.User
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.databinding.FragmentFeedBinding
import com.example.realworldconduitkotlin.databinding.FragmentYourFeedBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel
import com.example.realworldconduitkotlin.ui.auth.SignIn

class YourFeed: Fragment() {

    private var binding: FragmentYourFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentYourFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)


        //initializing adapter and recyclerView
        feedAdapter = ArticleFeedRVAdapter(object : ArticleFeedRVAdapter.OnArticleClickListener {
            //calling fun openArticle on article clicked
            override fun onArticleClicked(slug: String) = openArticle(slug)
        })
        binding?.rvYourFeed?.layoutManager = LinearLayoutManager(context)
        binding?.rvYourFeed?.adapter = feedAdapter




    return binding!!.root
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fetching the feed into recyclerView
        viewModel.fetchYourFeed()
        viewModel.feed.observe(viewLifecycleOwner) {
            feedAdapter.submitList(it)
        }
    }

    fun openArticle(articleId: String) {
        findNavController().navigate(
            R.id.action_feed_to_article,
            bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        //to avoid memory leak when view is destroyed
        binding = null
    }

}