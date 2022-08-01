package com.example.themovie.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("results") val results: List<MovieModel>,
)
