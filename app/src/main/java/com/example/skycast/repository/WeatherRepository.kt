package com.example.skycast.repository

import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.data.WeatherData
import com.example.skycast.utils.dateToString
import com.example.skycast.utils.timeToString

class WeatherRepository(
    private val api: OpenWeatherInterface
) {
    suspend fun getWeatherInfo(cityName: String): WeatherData {
        val response = api.findCityWeatherData("cairo")

        return WeatherData(
            dateTime = response.body()?.dt?.dateToString(),
            temperature = response.body()?.main?.temp.toString(),
            humidity = response.body()?.main?.humidity.toString() + " %",
            pressure = response.body()?.main?.pressure.toString() + " mBar",
            visibility = response.body()?.visibility.toString() + " Km",
            sunrise = response.body()?.sys?.sunrise?.timeToString(),
            sunset = response.body()?.sys?.sunset?.timeToString(),
            discription = response.body()?.weather?.get(0)?.description
        )

    }
}