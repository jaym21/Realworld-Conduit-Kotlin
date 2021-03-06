package com.example.api

import com.example.api.services.ConduitAPI
import com.example.api.services.ConduitAuthAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://conduit.productionready.io/api/"

object ConduitClient {

    var authToken: String? = null

    //setting authToken
    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization", "Token $it")
                .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
//        .readTimeout(5, TimeUnit.SECONDS)
//        .connectTimeout(8, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())


    val publicApi = retrofitBuilder
            .client(okHttpBuilder.build())
            .build()
            .create(ConduitAPI::class.java)

    val authApi = retrofitBuilder
            .client(okHttpBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ConduitAuthAPI::class.java)
}