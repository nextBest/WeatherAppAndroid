package com.nextbest.weatherappandroid.data.repository

import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.network.WeatherApi
import io.reactivex.Single

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {

    override fun searchLocationByCoordinates(
        latitude: Double,
        longitude: Double
    ): Single<WeatherData> {
        return weatherApi.getLocacationByCoordinates("$latitude,$longitude")
            .flatMap { locationList ->
                locationList.minBy { it.woeid }?.let {
                    return@flatMap weatherApi.getWeatherDetails(it.woeid.toString())
                } ?: run {
                    throw WeatherApi.ApiErrors.NoLocationForCoordinates()
                }
            }
    }

    override fun getWeatherInfo(woeid: Int) = weatherApi.getWeatherDetails(woeid.toString())

    override fun searchCity(query: String) = weatherApi.getLocationByQuery(query)

}