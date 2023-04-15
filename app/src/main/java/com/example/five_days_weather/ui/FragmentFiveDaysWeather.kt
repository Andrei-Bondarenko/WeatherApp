package com.example.five_days_weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.five_days_weather.api.WeatherApi
import com.example.five_days_weather.common.mvp.BaseMvpFragment
import com.example.five_days_weather.interactor.WeatherInteractor
import com.example.five_days_weather.model.WeatherData
import com.example.five_days_weather.repository.WeatherRemoteRepository
import com.example.utils.Client
import com.example.weather.R
import com.example.weather.databinding.ItemWeatherBinding
import timber.log.Timber


private const val BASE_URL = "https://api.openweathermap.org"

class FragmentFiveDaysWeather :
    BaseMvpFragment<WeatherContract.View, WeatherContract.Presenter>(R.layout.weather_fragment),
    WeatherContract.View {


    private val api: WeatherApi = Client.getClient(BASE_URL).create(WeatherApi::class.java)
    private lateinit var key: String
    private val remoteRepository = WeatherRemoteRepository(api)
    private val interactor = WeatherInteractor(remoteRepository)
    override val presenter: WeatherPresenter = WeatherPresenter(interactor)

    private lateinit var binding: ItemWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemWeatherBinding.inflate(inflater, container, false)
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
        Timber.d("______showData: $data")
        var gradusesC: Int = data.listWeather.first().main.temp.toInt()
        gradusesC -= 272
    }

    override fun showLoading(isVisible: Boolean) {

    }

}
