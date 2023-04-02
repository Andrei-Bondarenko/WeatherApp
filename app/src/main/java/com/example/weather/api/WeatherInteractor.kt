package com.example.weather.api

import com.example.weather.api.repository.RemoteRepository
import com.example.weather.model.WeatherData

class WeatherInteractor(
    private val remoteRepository: RemoteRepository
) {
    fun getWeatherData(city:String, key: String): WeatherData {
        return remoteRepository.getWeatherData(city,key)
    }
}