package com.nextbest.weatherappandroid.data.model

data class Location(
    val distance: Int?,
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)