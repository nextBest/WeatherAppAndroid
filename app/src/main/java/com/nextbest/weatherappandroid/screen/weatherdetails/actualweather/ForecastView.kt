package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.nextbest.weatherappandroid.BuildConfig
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Weather
import com.nextbest.weatherappandroid.utils.getDayName
import com.nextbest.weatherappandroid.utils.temperature
import kotlinx.android.synthetic.main.forecast_view.view.*

class ForecastView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.forecast_view, this)
    }

    fun setupView(weather: Weather) {
        day.text = weather.applicable_date.getDayName()
        Glide.with(context).load(BuildConfig.IMAGE_PATH + weather.weather_state_abbr + ".png")
            .into(weatherImage)
        minTemp.text = weather.min_temp.temperature()
        maxTemp.text = weather.max_temp.temperature()
    }

}
