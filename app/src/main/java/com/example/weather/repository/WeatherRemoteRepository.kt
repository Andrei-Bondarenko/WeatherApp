package com.example.weather.repository

import com.example.weather.api.WeatherApi
import com.example.weather.api.model.WeatherDataResponse
import com.example.weather.model.WeatherConverter
import com.example.weather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherRemoteRepository(
    private val api: WeatherApi
) : WeatherRepository {

    private lateinit var weatherData: WeatherData

    override fun getWeatherData(city: String, key: String): WeatherData {
        api.getWeatherData(city, key).enqueue(object : Callback<WeatherDataResponse> {
            override fun onResponse(
                call: Call<WeatherDataResponse>,
                response: Response<WeatherDataResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    weatherData = WeatherConverter.fromNetwork(response.body()!!)
                }
            }

            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                Timber.e("Get data error ", t.message)
            }

        })
        return weatherData
    }
}