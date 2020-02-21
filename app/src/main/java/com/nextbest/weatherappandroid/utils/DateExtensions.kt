package com.nextbest.weatherappandroid.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.getTimeOfDay(): TimeOfDay {
    Calendar.getInstance().apply {
        time = this@getTimeOfDay
        return@getTimeOfDay when (get(Calendar.HOUR_OF_DAY)) {
            in 6..20 -> TimeOfDay.DAY
            else -> TimeOfDay.NIGHT
        }

    }
}

fun Date.getDayName(): String {
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(this)
}

enum class TimeOfDay {
    DAY, NIGHT
}