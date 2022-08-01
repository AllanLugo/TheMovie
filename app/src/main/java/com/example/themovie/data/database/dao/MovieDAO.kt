package com.example.themovie.data.database.dao

import androidx.room.*
import com.example.themovie.data.database.entities.MovieEntitiy

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie_table")
    suspend fun getSavedMovies():List<MovieEntitiy>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie:List<MovieEntitiy>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovies()
}
