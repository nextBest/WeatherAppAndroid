package com.nextbest.weatherappandroid.utils

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

enum class TimeOfDay {
    DAY, NIGHT
}