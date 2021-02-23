package com.example.api.services

import com.example.api.models.entities.UserCredentials
import com.example.api.models.requests.SignUpRequest
import com.example.api.models.responses.ArticlesResponse
import com.example.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ConduitAPI {

    @POST("users")
    suspend fun signupUser(
        @Body userCredentials: SignUpRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favourited") favourited: String? = null,
        @Query("tag") tag: String? = null

    ): Response<ArticlesResponse>
}