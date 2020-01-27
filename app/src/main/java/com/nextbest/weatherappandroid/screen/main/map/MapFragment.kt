package com.nextbest.weatherappandroid.screen.main.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment


class MapFragment : BaseViewModelFragment<MapViewModel>(), OnMapReadyCallback {

    override fun getViewModelClass() = MapViewModel::class.java

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (googleMap == null) {
            (childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment).getMapAsync(
                this
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
    }
}
