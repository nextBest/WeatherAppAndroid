package com.nextbest.weatherappandroid.screen

import androidx.lifecycle.ViewModel
import com.nextbest.weatherappandroid.utils.KeyValueStorage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {

    private var compositeDisposable = CompositeDisposable()
    private var isViewModelCreated = false

    fun setParameters(
        argumentsKeyValueStorage: KeyValueStorage?,
        instanceStateKeyValueStorage: KeyValueStorage?
    ) {
        if (!isViewModelCreated) {
            isViewModelCreated = true
            init(argumentsKeyValueStorage, instanceStateKeyValueStorage)
        }
    }

    open fun init(
        argumentsKeyValueStorage: KeyValueStorage?,
        instanceStateKeyValueStorage: KeyValueStorage?
    ) {
    }

    open fun writeToInstanceState(keyValueStorage: KeyValueStorage) {
    }

    fun addSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
