package com.example.weather

import com.example.weather.api.model.WeatherData

interface MainContract {
    interface View {
        fun showData(data: WeatherData)
        fun showError(t:Throwable)
    }

    interface Presenter {
        fun getData(key: String, city: String)
        fun attach(view: MainContract.View)
        fun detach()
    }
}