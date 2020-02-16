package com.nextbest.weatherappandroid.screen.weatherdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.screen.weatherdetails.actualweather.ActualWeatherFragment
import com.nextbest.weatherappandroid.utils.pushFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_weather_details.*

class WeatherDetailsActivity : DaggerAppCompatActivity(), ActualWeatherFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        if (savedInstanceState == null) {
            intent?.extras?.run {
                pushFragment(
                    ActualWeatherFragment.newInstance(
                        getSerializable(WEATHER_DATA) as? WeatherData, getSerializable(
                            LOCATION
                        ) as? Location
                    )
                )
            }
        }
    }

    override fun setTitle(title: String) {
        weatherToolbar.title = title
    }

    companion object {
        private const val WEATHER_DATA = "WEATHER_DATA"
        private const val LOCATION = "LOCATION"

        fun newIntent(
            context: Context,
            weatherData: WeatherData? = null,
            location: Location? = null
        ) = Intent(context, WeatherDetailsActivity::class.java).apply {
            weatherData?.let {
                putExtra(WEATHER_DATA, it)
            }
            location?.let {
                putExtra(LOCATION, it)
            }
        }
    }
}
