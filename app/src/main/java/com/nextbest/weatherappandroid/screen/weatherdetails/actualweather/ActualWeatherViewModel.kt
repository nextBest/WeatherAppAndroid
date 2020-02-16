package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import com.nextbest.weatherappandroid.utils.KeyValueStorage
import javax.inject.Inject

class ActualWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    companion object {
        private const val WEATHER_DATA = "WEATHER_DATA"
        private const val LOCATION = "LOCATION"

        fun writeToBundle(
            keyValueStorage: KeyValueStorage,
            weatherData: WeatherData? = null,
            location: Location? = null
        ) {
            keyValueStorage.apply {
                weatherData?.let {
                    putSerializable(WEATHER_DATA, it)
                }
                location?.let {
                    putSerializable(LOCATION, it)
                }
            }
        }
    }
}