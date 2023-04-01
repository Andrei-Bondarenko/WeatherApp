package com.example.weather.api.repository

import com.example.weather.api.models.WeatherDataResponse

interface Repository {
    fun getWeatherData(city: String): WeatherDataResponse {
        return
    }
}