package com.example.weather

import com.example.weather.api.model.WeatherData

interface ViewWeather {
    fun showData(data: WeatherData)
    fun showError(t:Throwable)
}