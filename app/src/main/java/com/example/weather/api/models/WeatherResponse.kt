package com.example.weather.api.models

data class WeatherResponse(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
