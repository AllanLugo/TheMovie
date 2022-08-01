package com.example.themovie.repository

import com.example.themovie.data.database.dao.MovieDAO
import com.example.themovie.data.database.entities.MovieEntitiy
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.model.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieService,
    private val movieDao: MovieDAO){
    suspend fun getPlayingMoviesApi():List<Movie>{
        val response = api.getMovies()
        return response.map { it.toDomain() }
    }

    suspend fun getPlayingMoviesDB():List<Movie>{
        val response = movieDao.getSavedMovies()
        return response.map { it.toDomain() }
    }

    suspend fun insertMovies(movies:List<MovieEntitiy>){
        movieDao.insertMovies(movies)
    }
    suspend fun deleteDatabase(){
        movieDao.deleteMovies()
    }
}
