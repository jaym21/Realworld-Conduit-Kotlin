package com.example.realworldconduitkotlin.data

import com.example.api.ConduitClient
import com.example.api.models.entities.LoginData
import com.example.api.models.requests.LoginRequest
import com.example.api.models.responses.UserResponse

object UserRepo {

    val api = ConduitClient().api

    suspend fun login(email: String,  password: String): UserResponse? {
        val response = api.loginUser(LoginRequest(LoginData(email, password)))
        return response.body()
    }
}