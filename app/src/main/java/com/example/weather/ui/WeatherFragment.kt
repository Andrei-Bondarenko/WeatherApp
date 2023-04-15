package com.example.weather.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.five_days_weather.ui.FragmentFiveDaysWeather
import com.example.utils.Client
import com.example.utils.extensions.replace
import com.example.weather.R
import com.example.weather.api.WeatherApi
import com.example.weather.common.mvp.BaseMvpFragment
import com.example.weather.databinding.FragmentFiveDaysWeatherBinding
import com.example.weather.databinding.WeatherFragmentBinding
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.model.WeatherData
import com.example.weather.repository.WeatherRemoteRepository
import timber.log.Timber
private const val BASE_URL = "https://api.openweathermap.org"

class WeatherFragment :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.weather_fragment),
    WeatherContract.View {
    val celsiusUnits = getString(R.string.celsius)
    val key = getString(R.string.key)


    private val api = Client.getClient(BASE_URL).create(WeatherApi::class.java)
    private val remoteRepository = WeatherRemoteRepository(api)
    private val interactor = WeatherInteractor(remoteRepository)
    override val presenter: WeatherPresenter = WeatherPresenter(interactor)

    private lateinit var binding: WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        with(binding) {
            fiveDaysWeather.setOnClickListener {
                replace(fragment = FragmentFiveDaysWeather(),R.id.emptyContainer)
            }
            cityEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    showLoading(isLoading = true)
                    val cityName = cityEditText.text.toString()
                    Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                    Handler(Looper.getMainLooper()).postDelayed({
                        presenter.getData(
                            city = cityName,
                            key = key,
                            metric = celsiusUnits
                        )
                    }, 800)
                }
            })
        }
    }

    override fun showData(data: WeatherData) {
        Timber.d("______showData: %s", data)
        var gradusesC: Int = data.main.temp.toInt()
        gradusesC -= 272
        with(binding) {
            tempTextView.text = gradusesC.toString()
            weatherTextView.text =
                if (data.weather.isNotEmpty()) data.weather.first().description
                else data.name
            airHumidityTextView.text = data.main.humidity.toString()
            windSpeedTextView.text = data.wind.speed.toString()
            feelsLikeTextView.text = data.main.feels_like.toString()
            when (data.weather.first().description) {
                "clear sky" -> constraintContainer.setBackgroundResource(R.drawable.clear_sky)
                "overcast clouds" -> constraintContainer.setBackgroundResource(R.drawable.overcastclouds)
                "few clouds" -> constraintContainer.setBackgroundResource(R.drawable.few_clouds)
                "moderate rain" -> constraintContainer.setBackgroundResource(R.drawable.moderaterain)
                "scattered clouds" -> constraintContainer.setBackgroundResource(R.drawable.scattered_clouds)
                "broken clouds" -> constraintContainer.setBackgroundResource(R.drawable.scattered_clouds)
                "light rain" -> constraintContainer.setBackgroundResource(R.drawable.light_rain)
                "heavy intensity rain" -> constraintContainer.setBackgroundResource(R.drawable.heavyintensityrain)
                "light snow" -> constraintContainer.setBackgroundResource(R.drawable.light_snow)
                "smoke" -> constraintContainer.setBackgroundResource(R.drawable.smoke)
                else -> constraintContainer.setBackgroundResource(R.drawable.clear_sky)
            }
        }
    }


    override fun changeBackgroundImage(data: WeatherData) {
        TODO("Not yet implemented")
    }

    override fun showError(e: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun setContent(isVisible: Boolean) {
        binding.contentContainer.isVisible = isVisible
    }

    fun hideContent() {
        binding.constraintContainer.setBackgroundResource(R.drawable.clear_sky)
        binding.weatherTextView.text = ""
        binding.tempTextView.text = ""
        binding.progressBar.isVisible = true
    }

}