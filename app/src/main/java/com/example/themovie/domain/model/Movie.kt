package com.example.themovie.domain.model

import com.example.themovie.data.database.entities.MovieEntitiy
import com.example.themovie.model.MovieModel

data class Movie(val name: String, val adult: String, val overview: String, val release_date: String,  val id: String, val original_title: String, val original_language: String, val title: String, val backdrop_path: String, val popularity: String, val vote_count: String, val video: String, val vote_average: String)

fun MovieModel.toDomain() = Movie(
    name,
    adult,
    overview,
    release_date,
    id,
    original_title,
    original_language,
    title,
    backdrop_path,
    popularity,
    vote_count,
    video,
    vote_average

)
fun MovieEntitiy.toDomain() = Movie(
    name,
    adult,
    overview,
    release_date,
    id,
    original_title,
    original_language,
    title,
    backdrop_path,
    popularity,
    vote_count,
    video,
    vote_average

)
