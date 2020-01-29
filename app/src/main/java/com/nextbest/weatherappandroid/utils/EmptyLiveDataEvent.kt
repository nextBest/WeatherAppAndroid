package com.nextbest.weatherappandroid.utils

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

class EmptyLiveDataEvent : MutableLiveData<EmptyEvent>() {
    @MainThread
    fun call() {
        value = EmptyEvent()
    }
}