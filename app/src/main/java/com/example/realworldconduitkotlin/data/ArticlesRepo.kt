package com.example.realworldconduitkotlin.data

import com.example.api.ConduitClient

//an object for all functions related to articles
object ArticlesRepo {

    val publicApi = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun getGlobalFeed() = publicApi.getArticles()

    suspend fun getYourFeed() = authApi.getFeedArticles()
}