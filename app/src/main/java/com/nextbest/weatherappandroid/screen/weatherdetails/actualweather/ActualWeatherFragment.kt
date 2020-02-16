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
import com.nextbest.weatherappandroid.utils.setVisibility
import com.nextbest.weatherappandroid.views.ErrorView
import kotlinx.android.synthetic.main.fragment_actual_weather.*

class ActualWeatherFragment : BaseViewModelFragment<ActualWeatherViewModel>() {

    override fun getViewModelClass() = ActualWeatherViewModel::class.java
    private lateinit var listener: Listener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actual_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupErrorView()
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
        viewModel.showLoader.observe(this) {
            loaderView.setVisibility(it)
        }
        viewModel.showError.observe(this) {
            errorView.setVisibility(it)
        }
        viewModel.errorType.observe(this) {
            errorView.setErrorType(it)
        }
        viewModel.showData.observe(this) {
            // TODO show data
        }
    }

    private fun setupErrorView() {
        errorView.setReloadListener(object : ErrorView.Listener {
            override fun reload() {
                viewModel.getWeatherInfo()
            }
        })
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
