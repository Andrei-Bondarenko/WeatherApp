package com.example.weather.api.repository

import android.util.Log
import com.example.weather.api.WeatherApi
import com.example.weather.api.models.WeatherDataResponse
import com.example.weather.model.WeatherConverter
import com.example.weather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(
    private val api: WeatherApi
) : Repository {

    private lateinit var weatherData: WeatherData

    override fun getWeatherData(city: String, key: String): WeatherData {
        api.getWeatherData(city, key).enqueue(object : Callback:<WeatherDataResponse> {
            override fun onResponse(
                call: Call<WeatherDataResponse>,
                response: Response<WeatherDataResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    weatherData = WeatherConverter.fromNetwork(response.body()!!)
                }
            }
            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                Log.e("WeatherData", "onResponse: ${t.message}",t)
            }
        })
       return weatherData
    }


    fun fromNetwork(response: WeatherDataResponse): WeatherDataResponse {
        return response
    }
}