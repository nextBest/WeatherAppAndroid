package com.nextbest.weatherappandroid.data.model

import java.io.Serializable
import java.util.*

data class WeatherData(
    val consolidated_weather: List<Weather>,
    val latt_long: String,
    val location_type: String,
    val parent: Location,
    val sources: List<Source>,
    val sun_rise: Date,
    val sun_set: Date,
    val time: Date,
    val timezone: String,
    val timezone_name: String,
    val title: String,
    val woeid: Int
) : Serializable