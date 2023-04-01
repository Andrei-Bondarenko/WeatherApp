package com.example.weather.api.repository

interface WeatherRepository {
    fun getWeather(city: String) Call<WeatherData> {
        val weather
    }
}