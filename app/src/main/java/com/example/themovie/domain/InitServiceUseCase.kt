package com.example.themovie.domain

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.themovie.service.LocationHelper
import com.example.themovie.service.LocationService
import com.example.themovie.service.MyLocationListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class InitServiceUseCase @Inject constructor(@ApplicationContext private val context: Context, private val firebaseFirestore: FirebaseFirestore){
    fun initGeoService(){
        context?.let {
            ContextCompat.startForegroundService(it, Intent(context, LocationService::class.java))
            LocationHelper().startListeningUserLocation(
                it, object : MyLocationListener {
                    override fun onLocationChanged(location: Location?) {
                        var mLocation = location
                        mLocation?.let {
                            insertFirebaseData(it.latitude.toString(), it.longitude.toString())
                        }
                    }
                })
        }
    }
    private fun insertFirebaseData(latitude: String, longitude: String){
        val current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        val locationData = hashMapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "date" to formatted
        )

        firebaseFirestore.collection("locations")
            .add(locationData)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }
}
