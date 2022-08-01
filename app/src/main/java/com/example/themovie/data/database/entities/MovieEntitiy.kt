package com.example.themovie.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovie.domain.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntitiy (
    @ColumnInfo(name = "poster_path") val name: String,
    @ColumnInfo(name = "adult") val adult: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "original_title") val original_title: String,
    @ColumnInfo(name = "original_language") val original_language: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "popularity") val popularity: String,
    @ColumnInfo(name = "vote_count") val vote_count: String,
    @ColumnInfo(name = "video") val video: String,
    @ColumnInfo(name = "vote_average") val vote_average: String,
)

fun Movie.toDatabase() = MovieEntitiy(
        name =  name,
        adult =  adult,
        overview =  overview,
        release_date =  release_date,
        id =  id,
        original_title =  original_title,
        original_language =  original_language,
        title =  title,
        backdrop_path =  backdrop_path,
        popularity =  popularity,
        vote_count =  vote_count,
        video =  video,
        vote_average =  vote_average,)
