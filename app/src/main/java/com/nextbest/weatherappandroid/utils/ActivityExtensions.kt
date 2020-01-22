package com.nextbest.weatherappandroid.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nextbest.weatherappandroid.R

fun <T> AppCompatActivity.openActivity(it: Class<T>) {
    startActivity(Intent(this, it))
}

fun AppCompatActivity.pushFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
        .commit()
}