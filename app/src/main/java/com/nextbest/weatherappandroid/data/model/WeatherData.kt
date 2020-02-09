package com.nextbest.weatherappandroid.data.model

data class WeatherData(
    val consolidated_weather: List<Weather>,
    val latt_long: String,
    val location_type: String,
    val parent: Location,
    val sources: List<Source>,
    val sun_rise: String,
    val sun_set: String,
    val time: String,
    val timezone: String,
    val timezone_name: String,
    val title: String,
    val woeid: Int
)