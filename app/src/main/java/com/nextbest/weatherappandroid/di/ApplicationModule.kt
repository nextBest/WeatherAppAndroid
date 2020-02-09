package com.nextbest.weatherappandroid.di

import android.app.Application
import android.content.Context
import com.nextbest.weatherappandroid.data.local.SharedPreferencesStorage
import com.nextbest.weatherappandroid.data.local.SharedPreferencesStorageImpl
import com.nextbest.weatherappandroid.data.network.NetworkService
import com.nextbest.weatherappandroid.data.network.WeatherApi
import com.nextbest.weatherappandroid.data.repository.UserRepository
import com.nextbest.weatherappandroid.data.repository.UserRepositoryImpl
import com.nextbest.weatherappandroid.data.repository.WeatherRepository
import com.nextbest.weatherappandroid.data.repository.WeatherRepositoryImpl
import com.nextbest.weatherappandroid.utils.AndroidResourcesProvider
import com.nextbest.weatherappandroid.utils.ResourcesProvider
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
    fun provideNetworkService(context: Context): NetworkService = NetworkService(context)

    @Provides
    @Singleton
    fun provideWeatherRepository(networkService: NetworkService): WeatherRepository {
        return WeatherRepositoryImpl(networkService.getRetrofit().create(WeatherApi::class.java))
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferencesStorage: SharedPreferencesStorage): UserRepository {
        return UserRepositoryImpl(sharedPreferencesStorage)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesStorage(context: Context): SharedPreferencesStorage {
        return SharedPreferencesStorageImpl(context)
    }

    @Provides
    @Singleton
    fun provideResourcesProvider(context: Context): ResourcesProvider {
        return AndroidResourcesProvider(context)
    }
}