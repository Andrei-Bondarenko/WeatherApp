package com.example.weather.start_page.ui

import android.util.Log
import com.example.weather.common.mvp.BasePresenter
import com.example.weather.interactor.WeatherInteractor

class DefaultPagePresenter(private val interactor: WeatherInteractor) : BasePresenter<DefaultPageContract.View>(),
    DefaultPageContract.Presenter {

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