package com.nextbest.weatherappandroid.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nextbest.weatherappandroid.R

fun Fragment.showErrorSnackBar(message: String) {
    view?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).apply {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.errorColor))
            show()
        }
    }
}