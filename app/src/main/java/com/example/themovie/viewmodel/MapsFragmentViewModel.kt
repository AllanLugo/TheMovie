package com.example.themovie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.InitServiceUseCase
import com.example.themovie.domain.model.Location
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.firestore.ktx.toObject

@HiltViewModel
class MapsFragmentViewModel @Inject constructor(
    private val initServiceUseCase: InitServiceUseCase,
    private val firestore: FirebaseFirestore
): ViewModel() {
    val locationModel = MutableLiveData<List<Location>>()

    fun onMapInit(){
        viewModelScope.launch {
            firestore.collection("locations")
                .get()
                .addOnSuccessListener { documents ->
                    val list: MutableList<Location> = mutableListOf()
                    for (document in documents) {
                        list.add(document.toObject())
                    }
                    locationModel.postValue(list)
                    Log.i("TAG", "Error getting documents:")
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                }

        }
    }
}

