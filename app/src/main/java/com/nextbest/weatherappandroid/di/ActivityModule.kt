package com.nextbest.weatherappandroid.di

import com.nextbest.weatherappandroid.screen.main.MainActivity
import com.nextbest.weatherappandroid.screen.main.MainActivityFragmentsModule
import com.nextbest.weatherappandroid.screen.splash.SplashActivity
import com.nextbest.weatherappandroid.screen.weatherdetails.WeatherDetailsActivity
import com.nextbest.weatherappandroid.screen.weatherdetails.WeatherDetailsActivityFragmentsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [WeatherDetailsActivityFragmentsModule::class])
    abstract fun contributeWeatherDetailsActivity(): WeatherDetailsActivity
}