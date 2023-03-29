package com.example.weather

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.weather.api.Client
import com.example.weather.api.WeatherApi
import com.example.weather.api.model.WeatherData
import com.example.weather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val BASE_URL = "https://api.openweathermap.org/"

class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val api: WeatherApi = Client.getClient(BASE_URL).create(WeatherApi::class.java)
    private lateinit var key: String
    private val presenter: MainContract.Presenter = MainPresenter(api)


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

    override fun showData(data: WeatherData) {
        Log.d("______", "showData: $data")
        with(binding) {
            tempTextView.text = data.main.temp.toString()
            weatherTextView.text =
                if (data.weather.isNotEmpty()) data.weather.first().description
                else data.name
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