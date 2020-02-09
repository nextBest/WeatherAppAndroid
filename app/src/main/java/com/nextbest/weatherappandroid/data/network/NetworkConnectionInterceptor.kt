package com.nextbest.weatherappandroid.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NetworkService.NetworkErrors.NoInternetConnection()
        }

        val builder = chain.request().newBuilder()
        try {
            return chain.proceed(builder.build())
        } catch (exception: IOException) {
            if (exception is ConnectException || exception is UnknownHostException || exception is SocketTimeoutException) {
                throw NetworkService.NetworkErrors.NoInternetConnection()
            } else {
                throw exception
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}