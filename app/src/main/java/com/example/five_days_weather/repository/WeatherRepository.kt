package com.example.five_days_weather.repository

import com.example.five_days_weather.model.WeatherData

interface WeatherRepository {
    fun getWeatherData(city: String,key: String): WeatherData
}