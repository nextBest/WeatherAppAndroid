package com.nextbest.weatherappandroid.utils

import java.text.DecimalFormat

fun Double.temperature(): String {
    return DecimalFormat("#").format(this) + " °C"
}