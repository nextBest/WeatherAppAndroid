package com.nextbest.weatherappandroid.screen.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.network.NetworkService
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import com.nextbest.weatherappandroid.utils.Event
import com.nextbest.weatherappandroid.views.ErrorView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private var searchCitySingle: Single<List<Location>>? = null

    private val _errorType = MutableLiveData<ErrorView.ErrorType>()
    val errorType: LiveData<ErrorView.ErrorType>
        get() = _errorType

    private val _cityList = MutableLiveData<List<Location>>()
    val cityList: LiveData<List<Location>>
        get() = _cityList

    private val searchScreenState = MediatorLiveData<SearchScreenState>().apply {
        value = SearchScreenState.INFO
        addSource(_errorType) {
            value = SearchScreenState.ERROR
        }
        addSource(_cityList) {
            value =
                if (_cityList.value != null && _cityList.value!!.isNotEmpty()) SearchScreenState.DATA else SearchScreenState.NO_DATA
        }
    }

    val showLoader: LiveData<Boolean> = Transformations.map(searchScreenState) {
        return@map it == SearchScreenState.LOADING
    }

    val showError: LiveData<Boolean> = Transformations.map(searchScreenState) {
        return@map it == SearchScreenState.ERROR
    }

    val showNoData: LiveData<Boolean> = Transformations.map(searchScreenState) {
        return@map it == SearchScreenState.NO_DATA
    }

    val showSearch: LiveData<Boolean> = Transformations.map(searchScreenState) {
        return@map it == SearchScreenState.INFO
    }

    val showList: LiveData<Boolean> = Transformations.map(searchScreenState) {
        return@map it == SearchScreenState.DATA
    }


    private val _goToWeatherDetailsScreen = MutableLiveData<Event<Location>>()
    val goToWeatherDetailsScreen: LiveData<Event<Location>>
        get() = _goToWeatherDetailsScreen

    private val _showPlaceOnMap = MutableLiveData<Event<String>>()
    val showPlaceOnMap: LiveData<Event<String>>
        get() = _showPlaceOnMap

    fun searchCity(query: String) {
        if (searchCitySingle != null) {
            return
        }

        searchCitySingle = weatherRepository.searchCity(query)
        subscribeSearchCitySingle()?.let {
            addSubscription(it)
        }
    }

    fun cellClicked(location: Location) {
        _goToWeatherDetailsScreen.value = Event(location)
    }

    fun showPlaceOnMap(location: Location) {
        _showPlaceOnMap.value = Event(location.latt_long)
    }

    private fun subscribeSearchCitySingle(): Disposable? {
        return searchCitySingle?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    searchScreenState.value = SearchScreenState.LOADING
                }
                .doAfterTerminate {
                    searchCitySingle = null
                }
                .subscribe({ locationList ->
                    _cityList.value = locationList
                }, { error ->
                    _errorType.value = when (error) {
                        is NetworkService.NetworkErrors.NoInternetConnection -> ErrorView.ErrorType.CONNECTION_ERROR
                        else -> ErrorView.ErrorType.UNKNOWN_ERROR
                    }
                })
        }
    }

    enum class SearchScreenState {
        INFO, LOADING, ERROR, NO_DATA, DATA
    }

}