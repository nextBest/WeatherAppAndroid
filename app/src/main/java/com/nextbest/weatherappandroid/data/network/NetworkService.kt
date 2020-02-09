package com.nextbest.weatherappandroid.data.network

import com.nextbest.weatherappandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class NetworkService {

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .client(createHttpClient())
            .baseUrl(BuildConfig.API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    fun getRetrofit() = retrofit

    companion object NetworkErrors {
        class NoInternetConnection(): Exception()
    }
}
