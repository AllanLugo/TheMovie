package com.example.themovie.repository

import com.example.themovie.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiClient {
    @GET("movie/now_playing?api_key=01a362d7eaa6b6171e2fdc5b37050766")
    suspend fun getPlayingMovies():Response<ResponseModel>
}
