package com.example.five_days_weather.ui

import com.example.five_days_weather.common.mvp.BasePresenter
import com.example.five_days_weather.interactor.WeatherInteractor
import timber.log.Timber

class WeatherPresenter(private val interactor: WeatherInteractor) : WeatherContract.Presenter,
    BasePresenter<WeatherContract.View>() {
    override fun getData(key: String, city: String) {
        view?.showLoading(true)

        try {
            val weatherData = interactor.getWeatherData(city, key)
            view?.showLoading(false)
            view?.showData(weatherData)
        } catch (t: Throwable) {
            Timber.e("DATA ERROR ---->>>>>", t.message.toString())
        } finally {
            view?.showLoading(false)
        }
    }


}