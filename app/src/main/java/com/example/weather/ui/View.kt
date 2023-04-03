package com.example.weather.ui

import com.example.weather.model.WeatherData

interface View {
    fun showData(data: WeatherData)
    fun showError(t: Throwable)
    fun showLoading(isLoading: Boolean)
    fun setContent(isVisible: Boolean)
}