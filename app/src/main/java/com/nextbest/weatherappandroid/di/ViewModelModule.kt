package com.nextbest.weatherappandroid.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}