package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.screen.BaseViewModel
import javax.inject.Inject

class ActualWeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel()