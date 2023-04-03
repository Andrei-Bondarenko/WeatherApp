package com.example.weather.ui

import android.util.Log
import com.example.weather.interactor.WeatherInteractor

class WeatherPresenter(private val interactor: WeatherInteractor) : Presenter {
    private var view: View? = null

    override fun attach(view: View) {
        this.view = view
    }

    override fun getData(key: String, city: String) {
        try {
            view?.showLoading(isLoading = true)
            val weatherData = interactor.getWeatherData(city,key)
            if (weatherData != null) view?.setContent(isVisible = true) else view?.setContent(isVisible = false)
            view?.showData(weatherData)
            view?.showLoading(isLoading = false)
        }catch (t:Throwable){
            Log.e("Get data error",t.message.toString())
        }finally {
            view?.showLoading(isLoading = false)
        }
    }

    override fun detach() {
        this.view = null
    }


}