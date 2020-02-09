package com.nextbest.weatherappandroid.data.repository

import com.nextbest.weatherappandroid.data.model.WeatherData
import io.reactivex.Single

interface WeatherRepository {
    fun searchLocationByCoordinates(latitude: Double, longitude: Double): Single<WeatherData>

    fun getWeatherInfo(woeid: Int): Single<WeatherData>
}