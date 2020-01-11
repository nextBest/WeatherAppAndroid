package com.nextbest.weatherappandroid

import com.nextbest.weatherappandroid.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherAppApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build().apply {
            inject(this@WeatherAppApplication)
        }
    }
}