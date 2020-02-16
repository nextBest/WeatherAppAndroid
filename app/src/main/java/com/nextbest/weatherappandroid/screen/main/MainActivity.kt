package com.nextbest.weatherappandroid.screen.main

import android.os.Bundle
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.data.model.Location
import com.nextbest.weatherappandroid.data.model.WeatherData
import com.nextbest.weatherappandroid.screen.main.map.MapFragment
import com.nextbest.weatherappandroid.screen.main.search.SearchFragment
import com.nextbest.weatherappandroid.screen.weatherdetails.WeatherDetailsActivity
import com.nextbest.weatherappandroid.utils.addFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), MapFragment.Listener, SearchFragment.Listener {

    private lateinit var mapFragment: MapFragment
    private lateinit var searchFragment: SearchFragment
    private var activeFragmentTag: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initChildren()
        } else {
            activeFragmentTag = savedInstanceState.getString(ACTIVE_FRAGMENT_TAG)
        }
        setupBottomNavigation()
        setTitle()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState.apply {
            putString(ACTIVE_FRAGMENT_TAG, activeFragmentTag)
        })
    }

    private fun initChildren() {
        mapFragment = MapFragment()
        searchFragment = SearchFragment()
        addFragment(mapFragment, MAP_FRAGMENT)
        addFragment(searchFragment, SEARCH_FRAGMENT, true)
        activeFragmentTag = MAP_FRAGMENT
        mainBottomNavigation.selectedItemId = R.id.actionMap
    }

    private fun setTitle() {
        mainToolbar.title =
            getString(if (activeFragmentTag == MAP_FRAGMENT) R.string.map_menu else R.string.search_menu)
    }

    private fun setupBottomNavigation() {
        mainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionMap -> {
                    changeActiveFragment(MAP_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionSearch -> {
                    changeActiveFragment(SEARCH_FRAGMENT)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun changeActiveFragment(newActiveFragmentTag: String) {
        supportFragmentManager.run {
            beginTransaction()
                .apply {
                    activeFragmentTag?.let { findFragmentByTag(it)?.let { fragment -> hide(fragment) } }
                    findFragmentByTag(newActiveFragmentTag)?.let {
                        show(it)
                    }
                }
                .commit()
        }
        activeFragmentTag = newActiveFragmentTag
        setTitle()
    }

    override fun goToWeatherDetailsScreen(weatherData: WeatherData) {
        startActivity(WeatherDetailsActivity.newIntent(this, weatherData = weatherData))
    }

    override fun goToWeatherDetailsScreen(location: Location) {
        startActivity(WeatherDetailsActivity.newIntent(this, location = location))
    }

    companion object {
        private const val MAP_FRAGMENT = "MAP_FRAGMENT"
        private const val SEARCH_FRAGMENT = "SEARCH_FRAGMENT"
        private const val ACTIVE_FRAGMENT_TAG = "ACTIVE_FRAGMENT_TAG"
    }
}
