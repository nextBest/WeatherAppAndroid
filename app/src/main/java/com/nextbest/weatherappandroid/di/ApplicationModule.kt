package com.nextbest.weatherappandroid.di

import android.app.Application
import android.content.Context
import com.nextbest.weatherappandroid.data.network.NetworkService
import com.nextbest.weatherappandroid.data.network.WeatherApi
import com.nextbest.weatherappandroid.data.repository.UserRepository
import com.nextbest.weatherappandroid.data.repository.UserRepositoryImpl
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.data.repository.WeatherRepositoryImpl
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

    @Provides
    @Singleton
    fun provideWeatherRepository(networkService: NetworkService): WeatherRepository {
        return WeatherRepositoryImpl(networkService.getRetorfit().create(WeatherApi::class.java))
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}