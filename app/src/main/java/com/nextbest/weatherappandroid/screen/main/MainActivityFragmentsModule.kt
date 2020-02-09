package com.nextbest.weatherappandroid.screen.main

import com.nextbest.weatherappandroid.screen.main.map.MapFragment
import com.nextbest.weatherappandroid.screen.main.map.MapModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun contributeMapFragmentInjector(): MapFragment
}