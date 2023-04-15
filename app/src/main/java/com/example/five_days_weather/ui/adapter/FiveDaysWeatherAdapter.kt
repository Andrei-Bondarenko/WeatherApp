package com.example.five_days_weather.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.five_days_weather.model.ListWeather
import com.example.weather.R

class FiveDaysWeatherAdapter : RecyclerView.Adapter<FiveDaysWeatherViewHolder>() {

    private val data = mutableListOf<ListWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysWeatherViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return FiveDaysWeatherViewHolder(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FiveDaysWeatherViewHolder, position: Int) {
        val listItem = data[position]
        holder.onBind(listItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<ListWeather>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
}