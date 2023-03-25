package com.example.weather

import retrofit2.Retrofit

object Client {
    fun getClient(baseURL: String) = Retrofit.Builder()
        .baseUrl(baseURL)
        .build()
}