package com.example.weather.ui

interface Presenter {
    fun getData(key: String, city: String)
    fun attach(view: View)
    fun detach()
}