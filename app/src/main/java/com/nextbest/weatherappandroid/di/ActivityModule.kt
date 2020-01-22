package com.nextbest.weatherappandroid.di

import com.nextbest.weatherappandroid.MainActivity
import com.nextbest.weatherappandroid.screen.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
}