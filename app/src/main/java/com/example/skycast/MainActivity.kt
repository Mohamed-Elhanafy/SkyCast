package com.example.skycast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.skycast.api.NetworkConnectionInterceptor
import com.example.skycast.api.OpenWeatherInterface
import kotlinx.coroutines.launch


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launch {
            val api: OpenWeatherInterface =
                OpenWeatherInterface.invoke(NetworkConnectionInterceptor(this@MainActivity))
            val response = api.findCityWeatherData("cairo")
            Log.i(TAG, "onCreate: call url is ${response.body()}")
        }
    }
}