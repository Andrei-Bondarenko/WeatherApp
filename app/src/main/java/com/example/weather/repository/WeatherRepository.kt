package com.example.weather.repository

import com.example.weather.model.WeatherData

interface WeatherRepository {
    fun getWeatherData(city: String,key: String): WeatherData
}