package com.nextbest.weatherappandroid.data.local

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferencesStorageImpl(context: Context) : SharedPreferencesStorage {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}