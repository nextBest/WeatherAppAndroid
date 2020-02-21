package com.nextbest.weatherappandroid.screen.weatherdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(weatherToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
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
