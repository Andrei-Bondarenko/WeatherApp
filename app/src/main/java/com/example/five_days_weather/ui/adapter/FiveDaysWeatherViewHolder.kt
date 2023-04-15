package com.example.five_days_weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.five_days_weather.model.ListWeather
import com.example.utils.extensions.dateFormat
import com.example.utils.extensions.getDrawable
import com.example.weather.databinding.ItemWeatherBinding

class FiveDaysWeatherViewHolder(
    private val binding: ItemWeatherBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun onBind(item: ListWeather) {
        val description = item.weather[0].description

        with(binding) {
            imageViewIconItem.setImageResource(getDrawable(description))
            textViewTemperature.text = item.tempDescription()
            textViewDescription.text = dateFormat(item.dt_txt)
        }
    }
}