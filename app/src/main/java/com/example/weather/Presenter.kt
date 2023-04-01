package com.example.weather

interface Presenter {
    fun getData(key: String, city: String)
    fun attach(view: ViewWeather)
    fun detach()
}