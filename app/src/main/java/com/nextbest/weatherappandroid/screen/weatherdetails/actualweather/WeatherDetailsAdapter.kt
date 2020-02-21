package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.WeatherData

class WeatherDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var weatherData: WeatherData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.cell_weather_header -> WeatherHeaderViewHolder.create(parent)
            R.layout.cell_weather_details -> WeatherDetailsViewHolder.create(parent)
            R.layout.cell_weather_forecast -> WeatherForecastViewHolder.create(parent)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.cell_weather_header -> (holder as WeatherHeaderViewHolder).bindData(weatherData?.consolidated_weather?.first())
            R.layout.cell_weather_details -> (holder as WeatherDetailsViewHolder).bindData(
                weatherData?.consolidated_weather?.first()
            )
            R.layout.cell_weather_forecast -> (holder as WeatherForecastViewHolder).bindData(weatherData?.consolidated_weather)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.cell_weather_header
            1 -> R.layout.cell_weather_details
            2 -> R.layout.cell_weather_forecast
            else -> throw IllegalArgumentException()
        }
    }

    fun setData(weatherData: WeatherData) {
        this.weatherData = weatherData
        notifyDataSetChanged()
    }
}