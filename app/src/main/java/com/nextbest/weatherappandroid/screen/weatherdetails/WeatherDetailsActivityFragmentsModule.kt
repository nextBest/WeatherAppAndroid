package com.nextbest.weatherappandroid.screen.weatherdetails

import com.nextbest.weatherappandroid.screen.weatherdetails.actualweather.ActualWeatherFragment
import com.nextbest.weatherappandroid.screen.weatherdetails.actualweather.ActualWeatherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherDetailsActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [ActualWeatherModule::class])
    abstract fun contributeActualWeatherFragmentInjector(): ActualWeatherFragment
}