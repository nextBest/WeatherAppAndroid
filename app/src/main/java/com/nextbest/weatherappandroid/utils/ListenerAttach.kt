package com.nextbest.weatherappandroid.utils

object ListenerAttach {
    inline fun <reified T> attachListener(listener: Any?): T {
        try {
            return listener as T
        } catch (e: ClassCastException) {
            throw ClassCastException(
                listener.toString() + " must implement " + T::class.java.simpleName
            )
        }
    }
}
