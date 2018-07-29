package com.example.david951.sharelocation.presentation.position

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.location.*
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.david951.sharelocation.R
import com.example.david951.sharelocation.presentation.showToast
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_position.*


class PositionFragment : Fragment(){

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var positionViewModel : PositionViewModel

    private val locationRequest = LocationRequest()

    companion object {
        fun newInstance() = PositionFragment()
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    location.let {
                        positionViewModel.passLatLng(it.toLatLng())
                        updateLocation(it.toLatLng())
                    }
                }
            }
        }
        positionViewModel = ViewModelProviders.of(this).get(PositionViewModel::class.java)
        positionViewModel.getLocationLiveData().observe(this , Observer {
            updateLocation(it)
        })
        fusedLocationProviderClient
                .lastLocation
                .addOnSuccessListener { updateLocation(it?.toLatLng()) }
        return inflater.inflate(R.layout.fragment_position , container , false)
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        fusedLocationProviderClient.requestLocationUpdates(locationRequest , locationCallback , null)
    }

    override fun onPause() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onPause()
    }

    private fun updateLocation(location: LatLng?) {
        location?.let {
            tv_latitude.text = it.latitude.toString()
            tv_longitude.text = it.longitude.toString()
        }
    }

    private fun Location.toLatLng() = LatLng(latitude , longitude)
}