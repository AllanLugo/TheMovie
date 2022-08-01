package com.example.themovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.InitServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val initServiceUseCase: InitServiceUseCase,
): ViewModel() {

    fun onPermisionsGaranted(){
        viewModelScope.launch {
            initServiceUseCase.initGeoService()
        }
    }
}
