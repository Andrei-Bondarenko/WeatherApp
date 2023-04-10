package com.example.five_days_weather.ui

import com.example.five_days_weather.common.mvp.MvpPresenter
import com.example.five_days_weather.common.mvp.MvpView
import com.example.five_days_weather.model.WeatherData

interface WeatherContract {
    interface View: MvpView {
        fun setContent(isVisible: Boolean)
        fun showData(data: WeatherData)
    }
    interface Presenter: MvpPresenter<View> {
        fun getData(key: String, city: String)
    }
}