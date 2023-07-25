package com.example.skycast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.Weather
import com.example.skycast.data.WeatherData
import com.example.skycast.repository.WeatherRepository
import com.example.skycast.utils.dateToString
import com.example.skycast.utils.timeToString
import kotlinx.coroutines.launch

private const val TAG = "WeatherInfoViewModel"

class WeatherInfoViewModel : ViewModel() {
    private val weatherRepository = WeatherRepository(OpenWeatherInterface.invoke())

    private val _weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoLiveData: LiveData<WeatherData> = _weatherInfoLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = _progressBarLiveData

    private val _weatherInfoFailureLiveData = MutableLiveData<String>()
    val weatherInfoFailureLiveData: LiveData<String> = _weatherInfoFailureLiveData

    fun getWeatherInfo(cityName: String) {
        _progressBarLiveData.postValue(true)
        viewModelScope.launch {
            val weatherData = weatherRepository.getWeatherInfo(cityName)
            checkWeatherData(weatherData)
            Log.i(TAG, "onCreate: weather data is $weatherData")
            _weatherInfoLiveData.value = weatherData
            _progressBarLiveData.postValue(false)


        }
    }

    private fun checkWeatherData(weatherData: WeatherData?) {
        if (weatherData == null) {
            _weatherInfoFailureLiveData.value = "Something went wrong"
        }
    }


}