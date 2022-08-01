package com.example.themovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovie.data.database.dao.MovieDAO
import com.example.themovie.data.database.entities.MovieEntitiy

@Database(entities = [MovieEntitiy::class], version = 1)
abstract class MovieDataBase:RoomDatabase() {
    abstract fun getMovieDao():MovieDAO
}
