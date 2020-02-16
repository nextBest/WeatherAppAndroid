package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.data.network.NetworkService
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import com.nextbest.weatherappandroid.utils.KeyValueStorage
import com.nextbest.weatherappandroid.views.ErrorView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActualWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private var weatherData: WeatherData? = null
    private var location: Location? = null
    private var weatherInfoSingle: Single<WeatherData>? = null

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _showLoader = MutableLiveData<Boolean>()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _showData = MutableLiveData<WeatherData>()
    val showData: LiveData<WeatherData>
        get() = _showData

    private val _errorType = MutableLiveData<ErrorView.ErrorType>()
    val errorType: LiveData<ErrorView.ErrorType>
        get() = _errorType

    val showError: LiveData<Boolean> = Transformations.map(_errorType) {
        return@map true
    }


    override fun init(
        argumentsKeyValueStorage: KeyValueStorage?,
        instanceStateKeyValueStorage: KeyValueStorage?
    ) {
        super.init(argumentsKeyValueStorage, instanceStateKeyValueStorage)
        getDataFromKeyValueStorage(argumentsKeyValueStorage)
        getDataFromKeyValueStorage(instanceStateKeyValueStorage)

        if (argumentsKeyValueStorage == null) {
            _title.value = weatherData?.let {
                return@let it.title
            } ?: run { location?.title ?: "" }
        }
        initData()
    }

    private fun initData() {
        if (weatherData == null) {
            getWeatherInfo()
        } else {
            _showData.value = weatherData
        }
    }

    fun getWeatherInfo() {
        if (weatherInfoSingle != null) {
            return
        }
        location?.let {
            weatherInfoSingle = weatherRepository.getWeatherInfo(it.woeid)
            subscribeWeatherInfoSingle()?.let { disposable ->
                addSubscription(disposable)
            }
        }
    }

    private fun subscribeWeatherInfoSingle(): Disposable? {
        return weatherInfoSingle?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _showLoader.value = true
                }
                .doAfterTerminate {
                    _showLoader.value = false
                    weatherInfoSingle = null
                }
                .subscribe({ weatherData ->
                    this.weatherData = weatherData
                    _showData.value = weatherData
                }, { error ->
                    _errorType.value = when (error) {
                        is NetworkService.NetworkErrors.NoInternetConnection -> ErrorView.ErrorType.CONNECTION_ERROR
                        else -> ErrorView.ErrorType.UNKNOWN_ERROR
                    }
                })
        }
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