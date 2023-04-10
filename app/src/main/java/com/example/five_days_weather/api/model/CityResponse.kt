package com.example.five_days_weather.api.model

data class CityResponse(
    val id:Int,
    val name:String,
    val coord:CoordResponse,
    val country:String,
    val population:Int,
    val timezone:Int,
    val sunrise:Int,
    val sunset:Int
)
