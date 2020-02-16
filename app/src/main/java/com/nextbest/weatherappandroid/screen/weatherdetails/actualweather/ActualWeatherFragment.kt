package com.nextbest.weatherappandroid.screen.weatherdetails.actualweather


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment
import com.nextbest.weatherappandroid.utils.BundleStorage
import com.nextbest.weatherappandroid.utils.ListenerAttach
import com.nextbest.weatherappandroid.utils.observe

class ActualWeatherFragment : BaseViewModelFragment<ActualWeatherViewModel>() {

    override fun getViewModelClass() = ActualWeatherViewModel::class.java
    private lateinit var listener: Listener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actual_weather, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = ListenerAttach.attachListener(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.title.observe(this) {
            listener.setTitle(it)
        }
    }

    interface Listener {
        fun setTitle(title: String)
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
