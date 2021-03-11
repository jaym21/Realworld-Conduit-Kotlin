package com.example.realworldconduitkotlin.data

import com.example.api.ConduitClient
import com.example.api.models.entities.LoginData
import com.example.api.models.entities.UpdateUserData
import com.example.api.models.entities.User
import com.example.api.models.entities.UserCredentials
import com.example.api.models.requests.LoginRequest
import com.example.api.models.requests.SignUpRequest
import com.example.api.models.requests.UpdateUserRequest

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

    suspend fun updateUser(username: String?, email: String?, password: String?, bio: String?, image: String?): User? {
        val response  = authApi.updateCurrentUser(UpdateUserRequest(UpdateUserData(bio, email, image, password, username)))

        return response.body()?.user
    }

    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user

    suspend fun getCurrentUser(token: String): User? {

        ConduitClient.authToken = token

        return authApi.getCurrentUser().body()?.user
    }

}