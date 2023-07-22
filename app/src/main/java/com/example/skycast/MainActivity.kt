package com.example.skycast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.skycast.api.NetworkConnectionInterceptor
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.WeatherData
import com.example.skycast.databinding.ActivityMainBinding
import com.example.skycast.utils.dateToString
import com.example.skycast.utils.timeToString
import kotlinx.coroutines.launch


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            val api: OpenWeatherInterface =
                OpenWeatherInterface.invoke(NetworkConnectionInterceptor(this@MainActivity))
            val response = api.findCityWeatherData("cairo")
            Log.i(TAG, "onCreate: call url is ${response.body()}")
            val weatherData = WeatherData(
                dateTime = response.body()?.dt?.dateToString(),
                temperature = response.body()?.main?.temp.toString() ,
                humidity = response.body()?.main?.humidity.toString()+ " %",
                pressure = response.body()?.main?.pressure.toString()+ " mBar",
                visibility = response.body()?.visibility.toString() + " Km" ,
                sunrise = response.body()?.sys?.sunrise?.timeToString(),
                sunset = response.body()?.sys?.sunset?.timeToString(),
                discription = response.body()?.weather?.get(0)?.description
            )

            Log.i(TAG, "onCreate: weather data is $weatherData")


            binding.tvDateTime.text = weatherData.dateTime
            binding.tvTemperature.text = weatherData.temperature
            binding.tvHumidityValue.text = weatherData.humidity
            binding.tvPressureValue.text = weatherData.pressure
            binding.tvVisibilityValue.text = weatherData.visibility
            binding.tvSunriseTime.text = weatherData.sunrise
            binding.tvSunsetTime.text = weatherData.sunset


        }
    }
}