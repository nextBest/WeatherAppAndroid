package com.nextbest.weatherappandroid.data.local

interface SharedPreferencesStorage {

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean
}