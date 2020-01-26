package com.nextbest.weatherappandroid.screen.main.map

import androidx.lifecycle.ViewModel
import com.nextbest.weatherappandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MapModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun binMapViewModel(viewModel: MapViewModel): ViewModel
}