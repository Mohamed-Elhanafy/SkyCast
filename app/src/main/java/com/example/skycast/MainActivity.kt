package com.example.skycast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.WeatherData
import com.example.skycast.databinding.ActivityMainBinding
import com.example.skycast.utils.dateToString
import com.example.skycast.utils.timeToString
import com.example.skycast.viewmodel.WeatherInfoViewModel
import kotlinx.coroutines.launch


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { WeatherInfoViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getWeatherInfo("cairo")

        //observe the weather info live data
        viewModel.weatherInfoLiveData.observe(this) { weatherData ->
            Log.i(TAG, "onCreate: weather info is $weatherData")
            binding.apply {
                tvDateTime.text = weatherData.dateTime
                tvTemperature.text = weatherData.temperature
                tvHumidityValue.text = weatherData.humidity
                tvPressureValue.text = weatherData.pressure
                tvVisibilityValue.text = weatherData.visibility
                tvSunriseTime.text = weatherData.sunrise
                tvSunsetTime.text = weatherData.sunset
                tvWeatherCondition.text = weatherData.discription
            }
        }

    }
}