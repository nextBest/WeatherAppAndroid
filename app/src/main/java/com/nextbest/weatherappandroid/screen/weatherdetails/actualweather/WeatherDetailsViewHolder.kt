package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nextbest.weatherappandroid.BuildConfig
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Weather
import com.nextbest.weatherappandroid.utils.airPressure
import com.nextbest.weatherappandroid.utils.milesToKm
import com.nextbest.weatherappandroid.utils.percent
import kotlinx.android.synthetic.main.cell_weather_details.view.*

class WeatherDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(weather: Weather?) {
        itemView.apply {
            weather?.let {
                Glide.with(context).load(BuildConfig.IMAGE_PATH + it.weather_state_abbr + ".png")
                    .into(weatherImage)
                humidity.text = it.humidity.percent()
                weatherVisibility.text = it.visibility.milesToKm()
                pressure.text = it.air_pressure.airPressure()
                predictability.text = it.predictability.percent()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherDetailsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_weather_details, parent, false)
            return WeatherDetailsViewHolder(view)
        }
    }
}