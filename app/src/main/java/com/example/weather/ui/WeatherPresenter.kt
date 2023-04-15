package com.example.weather.ui

import com.example.weather.common.mvp.BasePresenter
import com.example.weather.interactor.WeatherInteractor

class WeatherPresenter(
    private val interactor: WeatherInteractor
) : WeatherContract.Presenter,
    BasePresenter<WeatherContract.View>() {
    override fun getData(key: String, city: String, metric: String) {
        try {
            val weatherData = interactor.getWeatherData(city, key)
            weatherData.let { weather ->
                view?.changeBackgroundImage(weather)
                view?.showData(weather)
            }
        } catch (t: Throwable) {
            view?.showError(t.message.toString())
        } finally {
            view?.showLoading(isLoading = false)
        }
    }
}