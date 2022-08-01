package com.example.themovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.GetPlayingMoviesUseCase
import com.example.themovie.domain.model.Movie
import com.example.themovie.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMovieDBViewModel @Inject constructor(
    private val getPlayingMoviesUseCase: GetPlayingMoviesUseCase,
): ViewModel() {
    val movieModel = MutableLiveData<Movie>()

    fun OnCreate(){
        viewModelScope.launch {
            val result = getPlayingMoviesUseCase()
            movieModel.postValue(result[0])
        }
    }
}
