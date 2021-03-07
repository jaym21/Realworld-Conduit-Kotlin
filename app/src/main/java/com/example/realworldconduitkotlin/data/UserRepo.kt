package com.example.realworldconduitkotlin.data

import com.example.api.ConduitClient
import com.example.api.models.entities.LoginData
import com.example.api.models.entities.User
import com.example.api.models.entities.UserCredentials
import com.example.api.models.requests.LoginRequest
import com.example.api.models.requests.SignUpRequest

object UserRepo {

    val publicApi = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun signin(email: String,  password: String): User? {
        val response = publicApi.loginUser(LoginRequest(LoginData(email, password)))

        //setting the authToken if available
        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

    suspend fun signup(username: String, email: String, password: String):  User? {
        val response = publicApi.signupUser(SignUpRequest(UserCredentials(email, password, username)))

        ConduitClient.authToken = response.body()?.user?.token

        return response.body()?.user
    }

}