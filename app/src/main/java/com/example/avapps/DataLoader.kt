package com.example.avapps

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataLoader {
    private const val DATA_API_URL = "http://84.23.53.133"

    private val client =  OkHttpClient.Builder()
        .build()

    var retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(DATA_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DataApi::class.java)
}