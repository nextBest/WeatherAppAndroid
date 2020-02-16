package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment

class ActualWeatherFragment : BaseViewModelFragment<ActualWeatherViewModel>() {

    override fun getViewModelClass() = ActualWeatherViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actual_weather, container, false)
    }


}
