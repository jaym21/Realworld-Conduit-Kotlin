package com.example.realworldconduitkotlin.data

import com.example.api.ConduitClient
import com.example.api.services.ConduitAPI

//an object for all functions related to articles
object ArticlesRepo {

    val api = ConduitClient().api

    suspend fun getFeed() = api.getArticles()
}