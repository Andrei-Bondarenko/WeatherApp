package com.example.weather

import com.example.weather.api.models.WeatherDataResponse

interface ViewWeather {
    fun showData(data: WeatherDataResponse)
    fun showError(t:Throwable)
}