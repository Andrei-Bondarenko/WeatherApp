package com.example.five_days_weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.collections.List
import kotlin.math.roundToInt

@Parcelize
data class ListWeather(
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop:Int,
    val sys: Sys,
    val dt_txt: String,
): Parcelable {
    fun tempDescription(): String {
        val tempData = main.temp.roundToInt()
        val descriptionOfTemp = " °C"
        return buildString {
            append(tempData).append(descriptionOfTemp)
        }
    }
}
