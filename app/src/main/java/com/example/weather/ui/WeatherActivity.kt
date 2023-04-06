package com.example.weather.ui


import android.os.Bundle
import android.os.PersistableBundle
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding

class WeatherActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        with(binding) {
            setContentView(binding.root)
            val fragment = DefaultFragment()
            changeFragment(fragment, R.id.mainFragmentContainer)
        }
    }
}
