package com.example.weather

import android.util.Log
import com.example.weather.api.WeatherApi
import com.example.weather.api.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val api: WeatherApi): MainContract.Presenter {
    private var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getData(key: String, city: String) {
        api.getWeatherData(city, key).enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    Log.e("_______", "Weather Data-----${response}")
                    if (data != null) view?.showData(data)
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.e("________", "Error===> ${t.message}")
                view?.showError(t)
            }
        })
    }

}