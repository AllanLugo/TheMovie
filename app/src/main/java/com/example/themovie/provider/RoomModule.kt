package com.example.themovie.provider

import android.content.Context
import androidx.room.Room
import com.example.themovie.data.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MOVIE_DB_NAME = "movie_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDataBase::class.java, MOVIE_DB_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db:MovieDataBase) = db.getMovieDao()
}
