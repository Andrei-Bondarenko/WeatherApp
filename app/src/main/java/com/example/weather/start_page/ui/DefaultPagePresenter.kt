package com.example.weather.start_page.ui

import android.util.Log
import com.example.weather.common.mvp.BasePresenter
import com.example.weather.interactor.WeatherInteractor
import timber.log.Timber

class DefaultPagePresenter(private val interactor: WeatherInteractor) : BasePresenter<DefaultPageContract.View>(),
    DefaultPageContract.Presenter {

    override fun getData(key: String, city: String) {
        view?.showLoading(true)
        try {
            val weatherData = interactor.getWeatherData(city, key)
            view?.setContent(true)
            view?.showLoading(false)
            view?.showData(weatherData)
        } catch (t: Throwable) {
            Timber.e("Get data error ${t.message}")
        } finally {
            view?.showLoading(false)
        }
    }

}