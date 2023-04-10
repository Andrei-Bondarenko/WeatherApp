package com.example.five_days_weather.api.model

data class WeatherDataResponse(
    val cod:Int,
    val message:Int,
    val cnt:Int,
    val listWeather: List<ListWeatherResponse>,
    val city:CityResponse

)
