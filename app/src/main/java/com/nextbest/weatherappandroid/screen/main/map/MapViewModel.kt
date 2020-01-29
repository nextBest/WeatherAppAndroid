package com.nextbest.weatherappandroid.screen.main.map

import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private var searchLocationByCoordinatesSingle: Single<WeatherData>? = null

    fun tapOnMap(latitude: Double, longitude: Double) {
        // TODO set position on map
        searchLocationByCoordinates(longitude, longitude)
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
                .doAfterTerminate {
                    searchLocationByCoordinatesSingle = null
                }
                .subscribe({

                }, {

                })
        }
    }
}