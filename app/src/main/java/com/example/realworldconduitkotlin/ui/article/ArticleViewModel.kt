package com.example.realworldconduitkotlin.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.models.entities.Article
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {

    val publicApi = ConduitClient.publicApi

    private val _article = MutableLiveData<com.example.api.models.entities.Article>()
    val article: LiveData<Article> = _article

    //to get single article using slug(i.e id)
    fun fetchArticle(slug: String) {
        viewModelScope.launch {
            val response  = publicApi.getArticlesBySlug(slug)

            response.body()?.article?.let {
                _article.postValue(it)
            }
        }
    }
}