package com.nextbest.weatherappandroid.screen.main

import com.nextbest.weatherappandroid.screen.main.map.MapFragment
import com.nextbest.weatherappandroid.screen.main.map.MapModule
import com.nextbest.weatherappandroid.screen.main.search.SearchFragment
import com.nextbest.weatherappandroid.screen.main.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun contributeMapFragmentInjector(): MapFragment

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun contributeSearchFragmentInjector(): SearchFragment
}