package com.nextbest.weatherappandroid.screen

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextbest.weatherappandroid.di.ViewModelFactory
import com.nextbest.weatherappandroid.utils.BundleStorage
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseViewModelFragment<T : ViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var _viewModel: T
    val viewModel: T
        get() = _viewModel

    abstract fun getViewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
        (_viewModel as? BaseViewModel)?.setParameters(arguments?.let {
            BundleStorage(it)
        }, savedInstanceState?.let {
            BundleStorage(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        (_viewModel as? BaseViewModel)?.apply {
            writeToInstanceState(BundleStorage(outState))
        }
    }
}
