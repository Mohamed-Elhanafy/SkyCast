package com.example.skycast.api

import com.example.skycast.data.Weather
import com.example.skycast.utils.Constants.API_KEY
import com.example.skycast.utils.Constants.BASE_URL
import com.example.skycast.utils.Constants.WEATHER_UNIT
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

interface OpenWeatherInterface {
    @GET("weather")
    suspend fun findCityWeatherData(
        @Query("q") q: String,
        @Query("units") units: String = WEATHER_UNIT,
        @Query("appid") appid: String = API_KEY
    ): Response<Weather>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): OpenWeatherInterface {

            val url = BASE_URL
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherInterface::class.java)
        }
    }
}