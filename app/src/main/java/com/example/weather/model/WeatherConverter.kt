package com.example.weather.model

import com.example.weather.api.models.WeatherDataResponse

object WeatherConverter {
    fun fromNetwork(response: WeatherDataResponse): WeatherData {
        val data = WeatherData(
             wind = response.wind
        )
    }
}