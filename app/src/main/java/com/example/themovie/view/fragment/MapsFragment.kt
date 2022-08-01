package com.example.themovie.view.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.themovie.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.example.themovie.databinding.FragmentMapsBinding
import com.example.themovie.databinding.FragmentTheMovieDBBinding
import com.example.themovie.service.LocationHelper
import com.example.themovie.service.LocationService
import com.example.themovie.service.MyLocationListener
import com.example.themovie.viewmodel.MapsFragmentViewModel
import com.example.themovie.viewmodel.TheMovieDBViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private val vieModel: MapsFragmentViewModel by viewModels()
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var  googleMap: GoogleMap
    private val callback = OnMapReadyCallback { map ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap = map
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        vieModel.locationModel.observe(viewLifecycleOwner, Observer {
            it.forEach {
                val latlng:LatLng = LatLng(it.latitude!!.toDouble(), it.longitude!!.toDouble())
                googleMap.addMarker(
                    MarkerOptions()
                        .position(latlng)
                        .title(it.date)
                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))
            }


        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        vieModel.onMapInit()
    }
}
