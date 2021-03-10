package com.example.realworldconduitkotlin.ui.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.realworldconduitkotlin.R
import com.example.realworldconduitkotlin.databinding.FragmentArticleBinding

class Article : Fragment() {

    private var binding: FragmentArticleBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    private var articleId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding  = FragmentArticleBinding.inflate(inflater, container, false)

        //getting the article id from navigation from global or your feed
        arguments?.let {
            articleId =  it.getString(resources.getString(R.string.arg_article_id))
        }

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        //fetching the article using the article id or slug passed from your or global feed
        articleId?.let {
            articleViewModel.fetchArticle(it)
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({lifecycle}) {
            binding?.apply {
                tvArticleFragTitle.text = it.title
                tvArticleFragAuthor.text = it.author.username
                tvArticleFragArticleDate.text = it.createdAt
                tvArticleFragBody.text = it.body
                Glide.with(requireContext()).load(it.author.image).into(ivArticleFragAvatar)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}