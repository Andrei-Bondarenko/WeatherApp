package com.example.five_days_weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.R
import com.example.five_days_weather.api.WeatherApi
import com.example.five_days_weather.common.mvp.BaseMvpFragment
import com.example.five_days_weather.interactor.WeatherInteractor
import com.example.five_days_weather.model.WeatherData
import com.example.five_days_weather.repository.WeatherRemoteRepository
import com.example.weather.databinding.OneDayWeatherBinding
import com.example.weather.utils.Client

class FragmentSevenDaysWeather :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.weather_fragment),
    WeatherContract.View {
    private val baseUrl = "api.openweathermap.org/"


    private val api: WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private lateinit var key: String
    private val remoteRepository = WeatherRemoteRepository(api)
    private val interactor = WeatherInteractor(remoteRepository)
    override val presenter: WeatherPresenter = WeatherPresenter(interactor)

    private lateinit var binding: OneDayWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OneDayWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        key = getString(R.string.key)
        presenter.attach(this)
        with(binding) {

        }
    }



    override fun showData(data: WeatherData) {
        Log.d("______", "showData: $data")
        var gradusesC: Int = data.main.temp.toInt()
        gradusesC -= 272
    }

    override fun setContent(isVisible: Boolean) {
        TODO("Not yet implemented")
    }
}
