package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.WeatherData

class WeatherTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(weatherData: WeatherData?) {

    }

    companion object {
        fun create(parent: ViewGroup): WeatherTimeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_weather_time, parent, false)
            return WeatherTimeViewHolder(view)
        }
    }
}