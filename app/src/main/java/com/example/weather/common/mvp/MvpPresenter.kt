package com.example.weather.common.mvp

interface MvpPresenter<V : MvpView> {
    fun attach(view: V)
    fun detach()
}