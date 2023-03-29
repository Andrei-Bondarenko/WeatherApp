package com.example.weather.api
import com.example.weather.api.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    fun getWeatherData(@Query("q") cityName:String,
                       @Query("appid") appid:String
    ): Call<WeatherData>
}