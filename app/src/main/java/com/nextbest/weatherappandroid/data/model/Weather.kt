package com.nextbest.weatherappandroid.data.model

import java.io.Serializable
import java.util.*

data class Weather(
    val air_pressure: Double,
    val applicable_date: Date,
    val created: Date,
    val humidity: Int,
    val id: Long,
    val max_temp: Double,
    val min_temp: Double,
    val predictability: Int,
    val the_temp: Double,
    val visibility: Double,
    val weather_state_abbr: String,
    val weather_state_name: String,
    val wind_direction: Double,
    val wind_direction_compass: String,
    val wind_speed: Double
) : Serializable