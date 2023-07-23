package com.example.skycast.utils

import com.example.skycast.utils.Constants.DATE_FORMAT
import com.example.skycast.utils.Constants.TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

fun Int.timeToString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this.toString()
}

fun Int.dateToString(): String {
    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * 1000.toLong()

        val outputDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this.toString()
}

fun Double.kelvinToCelsius(): Int {

    return (this - 273.15).toInt()
}


fun Int.meterToKm(): Int {
    return this / 1000
}
