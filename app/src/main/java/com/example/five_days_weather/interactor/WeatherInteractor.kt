package com.example.five_days_weather.interactor

import com.example.five_days_weather.model.WeatherData
import com.example.five_days_weather.repository.WeatherRemoteRepository

class WeatherInteractor(
    private val remoteRepository: WeatherRemoteRepository
) {
    fun getWeatherData(city:String, key: String): WeatherData {
        return remoteRepository.getWeatherData(city,key)
    }
}