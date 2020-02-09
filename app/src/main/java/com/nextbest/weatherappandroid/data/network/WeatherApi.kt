package com.nextbest.weatherappandroid.data.network

import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.Exception

interface WeatherApi {
    @GET("location/search")
    fun getLocacationByCoordinates(@Query("lattlong") latLong: String): Single<List<Location>>

    @GET("location/{woeid}/")
    fun getWeatherDetails(@Path("woeid") woeid: String): Single<WeatherData>

    companion object ApiErrors {
        class NoLocationForCoordinates(): Exception()
    }
}