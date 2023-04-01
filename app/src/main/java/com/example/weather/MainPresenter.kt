package com.example.weather

import android.util.Log
import com.example.weather.api.WeatherApi
import com.example.weather.api.models.WeatherDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val api: WeatherApi): Presenter {
    private var view: ViewWeather? = null

    override fun attach(view: ViewWeather) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getData(key: String, city: String) {
        api.getWeatherData(city, key).enqueue(object : Callback<WeatherDataResponse> {
            override fun onResponse(call: Call<WeatherDataResponse>, response: Response<WeatherDataResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    Log.e("_______", "Weather Data-----${response}")
                    if (data != null) view?.showData(data)
                }
            }

            override fun onFailure(call: Call<WeatherDataResponse>, t: Throwable) {
                Log.e("________", "Error===> ${t.message}")
                view?.showError(t)
            }
        })
    }


}