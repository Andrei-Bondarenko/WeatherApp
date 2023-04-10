package com.example.five_days_weather.model

data class WeatherData(
    val cod:Int,
    val message:Int,
    val cnt:Int,
    val listWeather: List<ListWeather>,
    val city:City

)
