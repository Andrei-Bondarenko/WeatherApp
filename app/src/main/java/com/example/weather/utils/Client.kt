package com.example.weather.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    fun getClient(baseURL: String) = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
