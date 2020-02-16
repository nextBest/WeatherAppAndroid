package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import com.nextbest.weatherappandroid.utils.KeyValueStorage
import javax.inject.Inject

class ActualWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private var weatherData: WeatherData? = null
    private var location: Location? = null

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    override fun init(
        argumentsKeyValueStorage: KeyValueStorage?,
        instanceStateKeyValueStorage: KeyValueStorage?
    ) {
        super.init(argumentsKeyValueStorage, instanceStateKeyValueStorage)
        getDataFromKeyValueStorage(argumentsKeyValueStorage)
        getDataFromKeyValueStorage(instanceStateKeyValueStorage)

        _title.value = weatherData?.let {
            return@let it.title
        } ?: run { location?.title ?: "" }
    }

    override fun writeToInstanceState(keyValueStorage: KeyValueStorage) {
        super.writeToInstanceState(keyValueStorage)
        keyValueStorage.apply {
            putSerializable(WEATHER_DATA, weatherData)
            putSerializable(LOCATION, location)
        }
    }

    private fun getDataFromKeyValueStorage(keyValueStorage: KeyValueStorage?) {
        keyValueStorage?.apply {
            weatherData = getSerializable(WEATHER_DATA) as? WeatherData
            location = getSerializable(LOCATION) as? Location
        }
    }

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