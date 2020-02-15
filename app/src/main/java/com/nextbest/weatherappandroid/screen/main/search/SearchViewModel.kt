package com.nextbest.weatherappandroid.screen.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private val _showLoader = MutableLiveData<Boolean>().apply { value = false }
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _showError = MutableLiveData<Boolean>().apply { value = false }
    val showError: LiveData<Boolean>
        get() = _showError

    private val _showNoData = MutableLiveData<Boolean>().apply { value = false }
    val showNoData: LiveData<Boolean>
        get() = _showNoData

    private val _showSearch = MutableLiveData<Boolean>().apply { value = true }
    val showSearch: LiveData<Boolean>
        get() = _showSearch

    private val _showList = MutableLiveData<Boolean>().apply { value = false }
    val showList: LiveData<Boolean>
        get() = _showList


}