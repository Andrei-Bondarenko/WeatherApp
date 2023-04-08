package com.example.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.weather.R
import com.example.weather.api.WeatherApi
import com.example.weather.common.mvp.BaseMvpFragment
import com.example.weather.databinding.WeatherFragmentBinding
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.model.WeatherData
import com.example.weather.repository.WeatherRemoteRepository
import com.example.weather.start_page.ui.DefaultFragment
import com.example.weather.utils.Client
import com.example.weather.utils.replace

class WeatherFragment :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.weather_fragment),
    WeatherContract.View {
    private val baseUrl = "https://api.openweathermap.org/"


    private val api: WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private lateinit var key: String
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
        key = getString(R.string.key)
        presenter.attach(this)
        with(binding) {
            cityEditText.doAfterTextChanged {
                if (it != null) {
                    if (it.isNotEmpty()) {
                        presenter.getData(key, it.toString())
                    }
                    if (it.isEmpty()) {
                        hideContent()
                    }
                }
            }
            buttonBackToMyCity.setOnClickListener {
                replace(fragment = DefaultFragment(), R.id.emptyContainer)
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
            airHumidityTextView.text = data.main.humidity.toString()
            windSpeedTextView.text = data.wind.speed.toString()
            feelsLikeTextView.text = data.main.feels_like.toString()
            when (data.weather.first().description) {
                "clear sky" -> constraintContainer.setBackgroundResource(R.drawable.clearsky)
                "overcast clouds" -> constraintContainer.setBackgroundResource(R.drawable.overcastclouds)
                "few clouds" -> constraintContainer.setBackgroundResource(R.drawable.fewclouds)
                "moderate rain" -> constraintContainer.setBackgroundResource(R.drawable.moderaterain)
                "scattered clouds" -> constraintContainer.setBackgroundResource(R.drawable.scatteredclouds)
                "broken clouds" -> constraintContainer.setBackgroundResource(R.drawable.scatteredclouds)
                "light rain" -> constraintContainer.setBackgroundResource(R.drawable.lightrain)
                "heavy intensity rain" -> constraintContainer.setBackgroundResource(R.drawable.heavyintensityrain)
                "light snow" -> constraintContainer.setBackgroundResource(R.drawable.lightsnow)
                "smoke" -> constraintContainer.setBackgroundResource(R.drawable.smoke)
                else -> constraintContainer.setBackgroundResource(R.drawable.clearsky)
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun setContent(isVisible: Boolean) {
        binding.contentContainer.isVisible = isVisible
    }

    fun hideContent() {
        binding.constraintContainer.setBackgroundResource(R.drawable.clearsky)
        binding.weatherTextView.text = ""
        binding.tempTextView.text = ""
        binding.progressBar.isVisible = true
    }

}