package com.example.weather.ui


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.weather.R
import com.example.weather.api.WeatherApi
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.model.WeatherData
import com.example.weather.repository.WeatherRemoteRepository
import com.example.weather.utils.Client

class WeatherActivity : AppCompatActivity(), View {
    private val baseUrl = "https://api.openweathermap.org/"


    private val api: WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private lateinit var key: String
    private val remoteRepository = WeatherRemoteRepository(api)
    private val interactor = WeatherInteractor(remoteRepository)
    private val presenter: Presenter = WeatherPresenter(interactor)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        key = getString(R.string.key)
        presenter.attach(this)
        with(binding) {
            cityEditText.doAfterTextChanged {
                presenter.getData(key, it.toString())
            }
        }
    }

    override fun showData(data: WeatherData) {
        Log.d("______", "showData: $data")
        var gradusesC: Int = data.main.temp.toInt()
        gradusesC -= 272
        with(binding) {
            tempTextView.text = gradusesC.toString()
            weatherTextView.text =
                if (data.weather.isNotEmpty()) data.weather.first().description
                else data.name
            when (weatherTextView.toString()) {
//                "clear sky" -> setBackgroundResource(R.drawable.clearsky)
//                "overcast clouds" ->.setBackgroundResource(R.drawable.overcastclouds)
//                "few clouds" -> constraint.setBackgroundResource(R.drawable.fewclouds)
//                "moderate rain" -> constraint.setBackgroundResource(R.drawable.moderaterain)
//                "scattered clouds" -> constraint.setBackgroundResource(R.drawable.scatteredclouds)
//                "broken clouds" -> constraint.setBackgroundResource(R.drawable.scatteredclouds)
//                "light rain" -> constraint.setBackgroundResource(R.drawable.lightrain)
//                "heavy intensity rain" -> constraint.setBackgroundResource(R.drawable.heavyintensityrain)
//                else -> constraint.setBackgroundResource(R.drawable.clearsky)
            }
        }
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun setContent(isVisible: Boolean) {
        binding.contentContainer.isVisible = isVisible
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}