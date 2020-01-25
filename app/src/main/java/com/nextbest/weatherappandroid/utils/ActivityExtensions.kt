package com.nextbest.weatherappandroid.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nextbest.weatherappandroid.R

fun <T> AppCompatActivity.openActivity(it: Class<T>) {
    startActivity(Intent(this, it))
    finish()
}

fun AppCompatActivity.addFragment(fragment: Fragment, tag: String, hidden: Boolean = false) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragmentContainer, fragment, tag)
        .apply { if (hidden) hide(fragment) }
        .commit()
}