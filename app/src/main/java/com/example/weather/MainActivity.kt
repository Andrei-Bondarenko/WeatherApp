package com.example.weather


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.example.weather.api.Client
import com.example.weather.api.WeatherApi
import com.example.weather.api.models.WeatherDataResponse
import com.example.weather.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ViewWeather {
    private val baseUrl = "https://api.openweathermap.org/"

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val api: WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private lateinit var key: String
    private val presenter: Presenter = MainPresenter(api)

    val constraint: ConstraintLayout = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        key = getString(R.string.key)
        setContentView(binding.root)
        presenter.attach(this)
        with(binding) {
            cityEditText.doAfterTextChanged {
                presenter.getData(key, it.toString())
            }
        }
    }

    override fun showData(data: WeatherDataResponse) {
        Log.d("______", "showData: $data")
        with(binding) {
            var gradusesC: Int = data.main.temp.toInt()
            gradusesC -= 272
            tempTextView.text = gradusesC.toString()
            weatherTextView.text =
                if (data.weather.isNotEmpty()) data.weather.first().description
                else data.name
            when (weatherTextView.toString()) {
                "clear sky" -> constraint.setBackgroundResource(R.drawable.clearsky)
                "overcast clouds" -> constraint.setBackgroundResource(R.drawable.overcastclouds)
                "few clouds" -> constraint.setBackgroundResource(R.drawable.fewclouds)
                "moderate rain" -> constraint.setBackgroundResource(R.drawable.moderaterain)
                "scattered clouds" -> constraint.setBackgroundResource(R.drawable.scatteredclouds)
                "broken clouds" -> constraint.setBackgroundResource(R.drawable.scatteredclouds)
                "light rain" -> constraint.setBackgroundResource(R.drawable.lightrain)
                "heavy intensity rain" -> constraint.setBackgroundResource(R.drawable.heavyintensityrain)
               else -> constraint.setBackgroundResource(R.drawable.clearsky)
            }
        }
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}