package com.nextbest.weatherappandroid.di

import android.app.Application
import android.content.Context
import com.nextbest.weatherappandroid.data.network.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService = NetworkService()
}