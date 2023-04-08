package com.example.weather.start_page.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.weather.R
import com.example.weather.api.WeatherApi
import com.example.weather.common.mvp.BaseMvpFragment
import com.example.weather.databinding.FragmentDefaultBinding
import com.example.weather.interactor.WeatherInteractor
import com.example.weather.model.WeatherData
import com.example.weather.repository.WeatherRemoteRepository
import com.example.weather.ui.WeatherFragment
import com.example.weather.utils.Client
import com.example.weather.utils.replace

class DefaultFragment :
    BaseMvpFragment<DefaultPageContract.View, DefaultPageContract.Presenter>(R.layout.fragment_default),
    DefaultPageContract.View {


    private val baseUrl = "https://api.openweathermap.org/"


    private val api: WeatherApi = Client.getClient(baseUrl).create(WeatherApi::class.java)
    private var key: String? = null
    private val remoteRepository = WeatherRemoteRepository(api)
    private val interactor = WeatherInteractor(remoteRepository)
    override val presenter: DefaultPagePresenter = DefaultPagePresenter(interactor)

    private lateinit var binding: FragmentDefaultBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        key = getString(R.string.key)
        presenter.attach(this)
        presenter.getData(key.toString(), binding.myCityTextView.text.toString())

        binding.buttonShowOtherCity.setOnClickListener {
            replace(fragment = WeatherFragment(), R.id.emptyContainer)
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
            when (data.weather.first().description) {
                "clear sky" -> defaultFragmentContainer.setBackgroundResource(R.drawable.clearsky)
                "overcast clouds" -> defaultFragmentContainer.setBackgroundResource(R.drawable.overcastclouds)
                "few clouds" -> defaultFragmentContainer.setBackgroundResource(R.drawable.fewclouds)
                "moderate rain" -> defaultFragmentContainer.setBackgroundResource(R.drawable.moderaterain)
                "scattered clouds" -> defaultFragmentContainer.setBackgroundResource(R.drawable.scatteredclouds)
                "broken clouds" -> defaultFragmentContainer.setBackgroundResource(R.drawable.scatteredclouds)
                "light rain" -> defaultFragmentContainer.setBackgroundResource(R.drawable.lightrain)
                "heavy intensity rain" -> defaultFragmentContainer.setBackgroundResource(R.drawable.heavyintensityrain)
                "light snow" -> defaultFragmentContainer.setBackgroundResource(R.drawable.lightsnow)
                "smoke" -> defaultFragmentContainer.setBackgroundResource(R.drawable.smoke)
                else -> defaultFragmentContainer.setBackgroundResource(R.drawable.clearsky)
            }
        }
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