package com.example.api.models.requests


import com.example.api.models.entities.UpdateUserData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserRequest(
    @Json(name = "user")
    val user: UpdateUserData
)