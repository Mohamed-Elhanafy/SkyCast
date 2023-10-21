package com.example.skycast.di

import com.example.skycast.api.OpenWeatherInterface
import com.example.skycast.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository(OpenWeatherInterface.invoke())
    }

}