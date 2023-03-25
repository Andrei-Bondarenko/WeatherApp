package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://api.openweathermap.org/"
    private val api:WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private val key = "https://api.openweathermap.org/data/2.5/weather?q=Bishkek&appid=7465cd2201320080ec76abc3b7bb945d"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     var textView:TextView = findViewById(R.id.Weather)
        api.getWeatherData("Bishkek",key).enqueue(object:Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    Log.e("_______","Weather Data-----${}")
                }
            }
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                  Log.e("________","Error===> ${t.message}")
                Toast.makeText(this,"Error",Toast.LENGTH_LONG)
            }
        })

    }
}