package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Weather

class WeatherDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(weather: Weather?) {

    }

    companion object {
        fun create(parent: ViewGroup): WeatherDetailsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_weather_details, parent, false)
            return WeatherDetailsViewHolder(view)
        }
    }
}