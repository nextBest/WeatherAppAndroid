package com.nextbest.weatherappandroid.di

import android.app.Application
import com.nextbest.weatherappandroid.WeatherAppApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(weatherAppApplication: WeatherAppApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}