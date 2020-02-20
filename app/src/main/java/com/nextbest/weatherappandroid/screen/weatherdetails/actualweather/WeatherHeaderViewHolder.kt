package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nextbest.weatherappandroid.BuildConfig
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Weather
import com.nextbest.weatherappandroid.utils.temperature
import kotlinx.android.synthetic.main.cell_weather_header.view.*

class WeatherHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(weather: Weather?) {
        itemView.apply {
            weather?.let {
                Glide.with(context).load(BuildConfig.IMAGE_PATH + it.weather_state_abbr + ".png")
                    .into(itemView.weatherImage)
                itemView.weatherState.text = it.weather_state_name
                itemView.temperature.text = it.the_temp.temperature()
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherHeaderViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_weather_header, parent, false)
            return WeatherHeaderViewHolder(view)
        }
    }
}