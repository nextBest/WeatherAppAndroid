package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Weather
import kotlinx.android.synthetic.main.cell_weather_forecast.view.*

class WeatherForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(weatherList: List<Weather>?) {
        itemView.apply {
            weatherList?.let {
                for (index in 1 until it.size) {
                    when (index) {
                        1 -> forecastDayOne.setupView(it[index])
                        2 -> forecastDayTwo.setupView(it[index])
                        3 -> forecastDayThree.setupView(it[index])
                        4 -> forecastDayFour.setupView(it[index])
                        5 -> forecastDayFive.setupView(it[index])
                    }
                }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): WeatherForecastViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_weather_forecast, parent, false)
            return WeatherForecastViewHolder(view)
        }
    }
}