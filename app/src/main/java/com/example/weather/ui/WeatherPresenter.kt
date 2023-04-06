package com.example.weather.ui

import android.util.Log
import com.example.weather.R
import com.example.weather.interactor.WeatherInteractor

class WeatherPresenter(private val interactor: WeatherInteractor) : WeatherContract.Presenter,BasePresenter<WeatherContract.View>() {
    override fun getData(key: String, city: String) {
        view?.showLoading(true)
        try {
            val weatherData = interactor.getWeatherData(city, key)
            if (weatherData != null) view?.setContent(true) else view?.setContent(false)
            view?.showData(weatherData)
            view?.showLoading(false)
        } catch (t: Throwable) {
            Log.e("Get data error", t.message.toString())
        }
    }




}