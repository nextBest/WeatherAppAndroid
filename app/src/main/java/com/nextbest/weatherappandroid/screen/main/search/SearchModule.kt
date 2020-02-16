package com.nextbest.weatherappandroid.screen.main.search

import androidx.lifecycle.ViewModel
import com.nextbest.weatherappandroid.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun binSearchViewModel(viewModel: SearchViewModel): ViewModel
}