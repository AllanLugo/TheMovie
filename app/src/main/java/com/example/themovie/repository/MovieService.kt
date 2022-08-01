package com.example.themovie.repository

import com.example.themovie.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api: MovieApiClient){

    suspend fun getMovies(): List<MovieModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getPlayingMovies()
            response.body()?.results ?: emptyList()
        }
    }
}
