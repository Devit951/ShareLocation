package com.example.david951.sharelocation.presentation.position

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class PositionViewModel : ViewModel() {

    private val locationLiveData : MutableLiveData<LatLng> = MutableLiveData()

    fun getLocationLiveData() : LiveData<LatLng> = locationLiveData

    fun passLatLng(latLng: LatLng?) {
        if (locationLiveData.value == null){
            latLng?.let { locationLiveData.value = it }
        }
    }
}