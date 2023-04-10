package com.example.five_days_weather.api.model

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
