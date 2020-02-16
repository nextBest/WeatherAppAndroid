package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import androidx.lifecycle.ViewModel
import com.nextbest.weatherappandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActualWeatherModule {

    @Binds
    @IntoMap
    @ViewModelKey(ActualWeatherViewModel::class)
    abstract fun binActualWeatherViewModel(viewModel: ActualWeatherViewModel): ViewModel
}