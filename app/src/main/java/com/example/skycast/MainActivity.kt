package com.example.skycast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.WeatherData
import com.example.skycast.databinding.ActivityMainBinding
import com.example.skycast.utils.dateToString
import com.example.skycast.utils.timeToString
import com.example.skycast.viewmodel.WeatherInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.fragment.app.activityViewModels


private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : WeatherInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnViewWeather.setOnClickListener {
            var city = binding.etCityName.text.toString()
            lifecycleScope.launch {
                viewModel.getWeatherInfo(city)
            }
        }

        viewModel.progressBarLiveData.observe(this, Observer { isShowLoader ->
            if (isShowLoader)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })


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

        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->

            binding.apply {
                tvDateTime.visibility = View.GONE
                tvTemperature.visibility = View.GONE
                celesiusSymbol.visibility = View.GONE
                tvHumidityValue.visibility = View.GONE
                tvHumidityLabel.visibility = View.GONE
                tvPressureValue.visibility = View.GONE
                tvPressureLabel.visibility = View.GONE
                tvVisibilityValue.visibility = View.GONE
                tvVisibilityLabel.visibility = View.GONE
                tvErrorMessage.visibility = View.VISIBLE
                tvErrorMessage.text = errorMessage
            }
        })


    }
}