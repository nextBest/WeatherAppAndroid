package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment
import com.nextbest.weatherappandroid.utils.BundleStorage

class ActualWeatherFragment : BaseViewModelFragment<ActualWeatherViewModel>() {

    override fun getViewModelClass() = ActualWeatherViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actual_weather, container, false)
    }

    companion object {
        fun newInstance(weatherData: WeatherData? = null, location: Location? = null) =
            ActualWeatherFragment().apply {
                arguments = Bundle().apply {
                    ActualWeatherViewModel.writeToBundle(BundleStorage(this), weatherData, location)
                }
            }
    }

}
