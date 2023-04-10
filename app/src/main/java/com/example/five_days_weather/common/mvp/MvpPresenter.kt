package com.example.five_days_weather.common.mvp

interface MvpPresenter<V : MvpView> {
    fun attach(view: V)
    fun detach()
}