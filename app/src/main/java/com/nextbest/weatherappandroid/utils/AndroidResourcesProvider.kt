package com.nextbest.weatherappandroid.utils

import android.content.Context

class AndroidResourcesProvider(val context: Context) : ResourcesProvider {

    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }
}