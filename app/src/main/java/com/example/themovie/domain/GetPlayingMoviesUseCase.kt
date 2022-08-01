package com.example.themovie.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.themovie.data.database.entities.toDatabase
import com.example.themovie.domain.model.Movie
import com.example.themovie.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
class GetPlayingMoviesUseCase @Inject constructor(@ApplicationContext private val context: Context, private val repository: MovieRepository){
    suspend operator fun invoke():List<Movie>{

        return if(checkForInternet(context)){
            val movies =  repository.getPlayingMoviesApi()
            repository.deleteDatabase()
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        }else{
            repository.getPlayingMoviesDB()
        }
    }
    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}
