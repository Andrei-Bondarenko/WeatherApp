package com.example.weather.api.repository

import com.example.weather.api.WeatherApi
import com.example.weather.api.models.WeatherDataResponse
import com.example.weather.model.WeatherConverter
import retrofit2.Converter

class RemoteRepository(val api: WeatherApi): Repository {
    override fun getWeatherData(city: String): WeatherDataResponse {
        val weather = api.getWeatherData(city)
        return WeatherConverter.fromNetwork(weather)
    }


    fun fromNetwork(response: WeatherDataResponse): WeatherDataResponse {
        return response
    }
}