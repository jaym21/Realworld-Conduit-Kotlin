package com.example.api

import com.example.api.services.ConduitAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val BASE_URL = "https://conduit.productionready.io/api/"

class ConduitClient {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(ConduitAPI::class.java)
}