package com.nextbest.weatherappandroid.utils

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import java.util.*

class BundleStorage(private val bundle: Bundle) : KeyValueStorage {

    override fun putString(key: String, value: String?) {
        bundle.putString(key, value)
    }

    override fun putInt(key: String, value: Int) {
        bundle.putInt(key, value)
    }

    override fun putFloat(key: String, value: Float) {
        bundle.putFloat(key, value)
    }

    override fun putLong(key: String, value: Long) {
        bundle.putLong(key, value)
    }

    override fun putBoolean(key: String, value: Boolean) {
        bundle.putBoolean(key, value)
    }

    override fun putParcelable(key: String, value: Parcelable?) {
        bundle.putParcelable(key, value)
    }

    override fun putSerializable(key: String, value: Serializable?) {
        bundle.putSerializable(key, value)
    }

    override fun putParcelableArrayList(key: String, value: ArrayList<out Parcelable>?) {
        bundle.putParcelableArrayList(key, value)
    }

    override fun putStringArrayList(key: String, value: ArrayList<String>?) {
        bundle.putStringArrayList(key, value)
    }

    override fun getString(key: String): String? {
        return bundle.getString(key)
    }

    override fun getSerializable(key: String): Serializable? {
        return bundle.getSerializable(key)
    }

    override fun getInt(key: String): Int {
        return bundle.getInt(key)
    }

    override fun getFloat(key: String): Float {
        return bundle.getFloat(key)
    }

    override fun getLong(key: String): Long {
        return bundle.getLong(key)
    }

    override fun getBoolean(key: String): Boolean {
        return bundle.getBoolean(key)
    }

    override fun getParcelable(key: String): Parcelable? {
        return bundle.getParcelable(key)
    }

    override fun <T : Parcelable> getParcelableArrayList(key: String): ArrayList<out T>? {
        return bundle.getParcelableArrayList(key)
    }

    override fun getStringArrayList(key: String): ArrayList<String>? {
        return bundle.getStringArrayList(key)
    }
}
