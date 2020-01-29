package com.nextbest.weatherappandroid.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.distinct(): LiveData<T> {
    val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData()
    mediatorLiveData.addSource(this) {
        if (it != mediatorLiveData.value) {
            mediatorLiveData.value = it
        }
    }
    return mediatorLiveData
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    observe(lifecycleOwner, Observer { observer ->
        observer?.let {
            action(it)
        }
    })
}

fun <T> LiveData<Event<T>>.observeEvent(
    lifecycleOwner: LifecycleOwner,
    action: (T) -> Unit
) {
    observe(lifecycleOwner, Observer { eventWrapper ->
        eventWrapper.getContentIfNotHandled()?.let {
            action(it)
        }
    })
}

fun LiveData<EmptyEvent>.observeEmptyEvent(
    lifecycleOwner: LifecycleOwner,
    action: (Unit) -> Unit
) {
    observe(lifecycleOwner, Observer { emptyEvent ->
        emptyEvent?.getContentIfNotHandled()?.let {
            action(it)
        }
    })
}

fun <T> LiveData<T>.observeDistinct(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    distinct()
    observe(lifecycleOwner, Observer { observer ->
        observer?.let {
            action(it)
        }
    })
}
