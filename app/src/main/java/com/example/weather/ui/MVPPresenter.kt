package com.example.weather.ui

interface MVPPresenter<V> {
    fun attach(view: V)
    fun detach()
}