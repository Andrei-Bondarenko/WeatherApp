package com.example.five_days_weather.model

import kotlin.collections.List

data class ListWeather(
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop:Int,
    val sys: Sys,
    val dt_txt: String,
)
