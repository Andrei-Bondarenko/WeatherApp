package com.example.five_days_weather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    fun getWeatherData(
        @Query("q") cityName: String,
        @Query("appid") appid: String
    ): Call<WeatherDataResponse>
}