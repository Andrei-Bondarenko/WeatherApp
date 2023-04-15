package com.example.utils.extensions

import androidx.fragment.app.Fragment
import com.example.five_days_weather.ui.adapter.EnumWeatherDescriptionType
import com.example.weather.R
import kotlin.math.roundToInt

fun Any.dateFormat(date: String) = buildString {
    append(date.split(" ")[0].split("-")[2])
        .append(".")
        .append(date.split(" ")[0].split("-")[1])
        .append(".")
        .append(date.split(" ")[0].split("-")[0][2])
        .append(date.split(" ")[0].split("-")[0][3])
        .append(" / ")
        .append(date.split(" ")[1].split(":")[0])
        .append(":")
        .append(date.split(" ")[1].split(":")[1])
}

fun Fragment.tempDescription(temp: Float?): String {
    val descriptionOfTemp = requireContext().getString(R.string.celsius)
    val tempData = temp?.roundToInt()
    return buildString {
        append(tempData)
            .append(descriptionOfTemp)
    }
}

//TODO: уберешь из этого метода вариант else ->
// на каждый ENUM тип найди иконку в таком стиле, что я создал и подставь их в формате xml.
// Если будут вопросы пиши. Иконки брал из Material Google Icons ->
// https://fonts.google.com/icons?selected=Material+Symbols+Outlined:rainy:FILL@0;wght@400;GRAD@0;opsz@48&icon.query=weather&icon.platform=android

fun Any.getDrawable(type: String) =
    when (type) {
        EnumWeatherDescriptionType.CLEAR_SKY.description -> R.drawable.clear_sky
        EnumWeatherDescriptionType.FEW_CLOUDS.description -> R.drawable.few_clouds
        EnumWeatherDescriptionType.BROKEN_CLOUDS.description -> R.drawable.scattered_clouds
        EnumWeatherDescriptionType.SCATTERED_CLOUDS.description -> R.drawable.scattered_clouds
        EnumWeatherDescriptionType.OVERCAST_CLOUDS.description -> R.drawable.clouds
        EnumWeatherDescriptionType.LIGHT_SNOW.description -> R.drawable.light_snow
        else -> R.drawable.light_rain
    }