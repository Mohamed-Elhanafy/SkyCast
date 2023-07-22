package com.example.skycast.repository

import android.content.Context
import com.example.skycast.api.NetworkConnectionInterceptor
import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.Weather

class WeatherRepository(
    private val api: OpenWeatherInterface
) {

    suspend fun findCityWeather(cityName: String) {
        api.findCityWeatherData(cityName)
    }
}