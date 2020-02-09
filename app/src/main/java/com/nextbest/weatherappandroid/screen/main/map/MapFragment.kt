package com.nextbest.weatherappandroid.screen.main.map

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nextbest.weatherappandroid.R
import com.nextbest.weatherappandroid.screen.BaseViewModelFragment
import com.nextbest.weatherappandroid.utils.*
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : BaseViewModelFragment<MapViewModel>(), OnMapReadyCallback {

    override fun getViewModelClass() = MapViewModel::class.java
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(it)
        }
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (googleMap == null) {
            (childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment).getMapAsync(
                this
            )
        }
        checkLocationPermission()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.showMarkerOnMap.observe(this) {
            showMarkerOnMap(it.first, it.second)
        }
        viewModel.setCameraPosition.observe(this) {
            setCameraPosition(it.first, it.second)
        }
        viewModel.showLoader.observe(this) {
            loaderView.setVisibility(it)
        }
        viewModel.showErrorMessageSnackBar.observeEvent(this) {
            showErrorSnackBar(it)
        }
        viewModel.goToWeatherDetailsScreen.observeEvent(this) {
            // TODO go to weather details screen
        }
    }

    private fun checkLocationPermission() {
        if (context?.checkPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION) == true) {
            searchUserLocation()
        } else {
            activity?.let {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        it,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                ) {
                    showPermissionRationaleDialog()
                } else {
                    requestLocationPermission()
                }
            }
        }
    }

    private fun showMarkerOnMap(latitude: Double, longitude: Double) {
        val markerPosition = LatLng(latitude, longitude)
        googleMap?.let {
            it.clear()
            it.addMarker(MarkerOptions().position(markerPosition))
        }
    }

    private fun setCameraPosition(latitude: Double, longitude: Double) {
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 6f))
    }

    private fun requestLocationPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun showPermissionRationaleDialog() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.rational_dialog_title)
                .setMessage(R.string.rational_dialog_message)
                .setPositiveButton(
                    R.string.rational_dialog_accept_button
                ) { _, _ ->
                    requestLocationPermission()
                }
                .setNegativeButton(R.string.rational_dialog_reject_button, null)
                .show()
        }
    }

    private fun searchUserLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                viewModel.locationFind(it.latitude, it.longitude)
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }


    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        googleMap?.setOnMapClickListener {
            viewModel.tapOnMap(it.latitude, it.longitude)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            searchUserLocation()
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
