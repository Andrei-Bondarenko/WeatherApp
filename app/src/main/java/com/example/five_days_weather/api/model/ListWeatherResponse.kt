package com.example.five_days_weather.api.model

import kotlin.collections.List

data class ListWeatherResponse(
    val main: MainResponse,
    val weather: List<WeatherResponse>,
    val clouds: CloudsResponse,
    val wind: WindResponse,
    val visibility: Int,
    val pop:Int,
    val sys: SysResponse,
    val dt_txt: String,
)
