package com.example.weather.ui

import com.example.weather.common.mvp.MvpPresenter
import com.example.weather.common.mvp.MvpView
import com.example.weather.model.WeatherData

interface WeatherContract {
    interface View: MvpView {
        fun showLoading(isLoading: Boolean)
        fun setContent(isVisible: Boolean)
        fun showData(data: WeatherData)
        fun changeBackgroundImage(data: WeatherData)
    }
    interface Presenter: MvpPresenter<View> {
        fun getData(key: String, city: String, metric: String)
    }
}