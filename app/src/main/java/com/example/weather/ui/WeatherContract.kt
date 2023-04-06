package com.example.weather.ui

import com.example.weather.model.WeatherData

interface WeatherContract {
    interface View: MVPView {
        fun showLoading(isLoading: Boolean)
        fun setContent(isVisible: Boolean)
        fun showData(data: WeatherData)
    }
    interface Presenter: MVPPresenter<View> {
        fun getData(key: String, city: String)
    }
}