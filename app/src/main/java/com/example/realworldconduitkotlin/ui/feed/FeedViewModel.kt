package com.example.realworldconduitkotlin.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.Article
import com.example.realworldconduitkotlin.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel: ViewModel() {

    //making a MutableLiveData of List Article that will make up our feed
    private val _feed = MutableLiveData<List<Article>>()

    val feed: LiveData<List<Article>> = _feed

    //to fetch the global feed from api
    fun fetchGlobalFeed() {
        viewModelScope.launch {
            //posting the articles we got from api in created mutable list of LiveData
            ArticlesRepo.getGlobalFeed().body()?.articles.let { articles ->
                _feed.postValue(articles)
            }
        }
    }

    //to fetch your feed for user from api
    fun fetchYourFeed() {
        viewModelScope.launch {
            ArticlesRepo.getYourFeed().body()?.articles.let { articles ->
                _feed.postValue(articles)
            }
        }
    }
}