package com.nextbest.weatherappandroid.screen.main.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.network.NetworkService
import com.nextbest.weatherappandroid.data.network.WeatherApi
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import com.nextbest.weatherappandroid.utils.Event
import com.nextbest.weatherappandroid.utils.ResourcesProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val resourcesProvider: ResourcesProvider
) :
    BaseViewModel() {

    private var searchLocationByCoordinatesSingle: Single<WeatherData>? = null

    private val _showMarkerOnMap = MutableLiveData<Pair<Double, Double>>()
    val showMarkerOnMap: LiveData<Pair<Double, Double>>
        get() = _showMarkerOnMap

    private val _setCameraPosition = MutableLiveData<Pair<Double, Double>>()
    val setCameraPosition: LiveData<Pair<Double, Double>>
        get() = _setCameraPosition

    private val _showLoader = MutableLiveData<Boolean>().apply { value = false }
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _goToWeatherDetailsScreen = MutableLiveData<Event<WeatherData>>()
    val goToWeatherDetailsScreen: LiveData<Event<WeatherData>>
        get() = _goToWeatherDetailsScreen

    private val _showErrorMessageSnackBar = MutableLiveData<Event<String>>()
    val showErrorMessageSnackBar: LiveData<Event<String>>
        get() = _showErrorMessageSnackBar


    fun tapOnMap(latitude: Double, longitude: Double) {
        _showMarkerOnMap.value = Pair(latitude, longitude)
        searchLocationByCoordinates(latitude, longitude)
    }

    fun locationFind(latitude: Double, longitude: Double) {
        _setCameraPosition.value = Pair(latitude, longitude)
    }

    private fun searchLocationByCoordinates(latitude: Double, longitude: Double) {
        if (searchLocationByCoordinatesSingle != null) {
            return
        }

        searchLocationByCoordinatesSingle =
            weatherRepository.searchLocationByCoordinates(latitude, longitude)
        subscribeSearchLocationByCoordinatesSingle()?.let {
            addSubscription(it)
        }
    }

    private fun subscribeSearchLocationByCoordinatesSingle(): Disposable? {
        return searchLocationByCoordinatesSingle?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _showLoader.value = true
                }
                .doAfterTerminate {
                    _showLoader.value = false
                    searchLocationByCoordinatesSingle = null
                }
                .subscribe({ weatherData ->
                    _goToWeatherDetailsScreen.value = Event(weatherData)
                }, { error ->
                    val message = when (error) {
                        is WeatherApi.ApiErrors.NoLocationForCoordinates -> resourcesProvider.getString(
                            R.string.map_no_location_found_for_coordinates
                        )
                        is NetworkService.NetworkErrors.NoInternetConnection -> resourcesProvider.getString(
                            R.string.no_internet_connection
                        )
                        else -> resourcesProvider.getString(R.string.unknown_error)
                    }
                    _showErrorMessageSnackBar.value = Event(message)
                })
        }
    }
}