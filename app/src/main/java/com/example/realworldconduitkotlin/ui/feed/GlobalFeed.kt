package com.example.realworldconduitkotlin.ui.feed


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.adapters.ArticleFeedRVAdapter
import com.example.realworldconduitkotlin.databinding.FragmentGlobalFeedBinding
import com.example.realworldconduitkotlin.ui.auth.AuthViewModel

class GlobalFeed : Fragment() {

    private var binding: FragmentGlobalFeedBinding? = null
    lateinit var viewModel: FeedViewModel
    lateinit var feedAdapter: ArticleFeedRVAdapter
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentGlobalFeedBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)


        authViewModel.user.observe({lifecycle}) {
            Toast.makeText(context, "Logged in as ${it?.username}", Toast.LENGTH_SHORT).show()
        }


        //initializing adapter and recyclerView
        feedAdapter = ArticleFeedRVAdapter(object : ArticleFeedRVAdapter.OnArticleClickListener {
            //calling fun openArticle on article clicked
            override fun onArticleClicked(slug: String) = openArticle(slug)
        })
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