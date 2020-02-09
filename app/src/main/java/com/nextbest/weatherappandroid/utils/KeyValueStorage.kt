package com.nextbest.weatherappandroid.utils

import android.os.Parcelable
import java.io.Serializable
import java.util.*

interface KeyValueStorage {

    fun putString(key: String, value: String?)

    fun putInt(key: String, value: Int)

    fun putFloat(key: String, value: Float)

    fun putLong(key: String, value: Long)

    fun putBoolean(key: String, value: Boolean)

    fun putParcelable(key: String, value: Parcelable?)

    fun putSerializable(key: String, value: Serializable?)

    fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>?)

    fun putStringArrayList(key: String, value: ArrayList<String>?)

    fun getString(key: String): String?

    fun getInt(key: String): Int

    fun getFloat(key: String): Float

    fun getLong(key: String): Long

    fun getBoolean(key: String): Boolean

    fun getSerializable(key: String): Serializable?

    fun getParcelable(key: String): Parcelable?

    fun <T : Parcelable> getParcelableArrayList(key: String): ArrayList<out T>?

    fun getStringArrayList(key: String): ArrayList<String>?
}
