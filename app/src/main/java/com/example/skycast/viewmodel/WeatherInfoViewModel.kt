package com.example.skycast.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycast.api.NetworkConnectionInterceptor
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.Weather
import com.example.skycast.data.WeatherData
import com.example.skycast.utils.timeToString
import kotlinx.coroutines.launch

private const val TAG = "WeatherInfoViewModel"

class WeatherInfoViewModel : ViewModel() {
    val weatherInfoLiveData = MutableLiveData<Weather>()

    fun getWeatherInfo(cityName: String) {
        Log.d(TAG, "getWeatherInfo: $cityName")
        viewModelScope.launch {
/*            val api: OpenWeatherInterface =
                OpenWeatherInterface.invoke(NetworkConnectionInterceptor())
            val response = api.findCityWeatherData("cairo")
            Log.i(TAG, "onCreate: call url is ${response.body()}")*/


        }

    }
}