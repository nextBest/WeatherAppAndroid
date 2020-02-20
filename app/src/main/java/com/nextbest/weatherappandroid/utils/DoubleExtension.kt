package com.nextbest.weatherappandroid.utils

import java.text.DecimalFormat

const val MILE_IN_KM = 1.609344

fun Double.temperature(): String {
    return DecimalFormat("#").format(this) + " °C"
}

fun Double.milesToKm(): String {
    val km = MILE_IN_KM * this
    return DecimalFormat("#.##").format(km) + " km"
}

fun Double.airPressure(): String {
    return DecimalFormat("#").format(this) + " mBar"
}