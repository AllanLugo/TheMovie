package com.example.themovie.model

import com.google.gson.annotations.SerializedName

data class MovieModel (
    @SerializedName("poster_path") val name: String,
    @SerializedName("adult") val adult: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("genre_ids") val genre_ids: List<Int>,
    @SerializedName("id") val id: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("title") val title: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("vote_count") val vote_count: String,
    @SerializedName("video") val video: String,
    @SerializedName("vote_average") val vote_average: String,
)
