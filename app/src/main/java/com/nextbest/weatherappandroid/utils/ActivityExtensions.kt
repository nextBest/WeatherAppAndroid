package com.nextbest.weatherappandroid.utils

import android.app.Activity
import android.content.Intent

fun <T> Activity.openActivity(it: Class<T>) {
    startActivity(Intent(this, it))
}