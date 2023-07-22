package com.example.skycast.data

data class WeatherData(
    var dateTime: String? = "",
    var temperature: String = "0",
    var humidity: String = "",
    var pressure: String = "",
    var visibility: String = "",
    var sunrise: String? = "",
    var sunset: String? = "",
    var discription: String? = ""
)