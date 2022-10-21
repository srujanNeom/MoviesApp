package com.example.moviesapp.details.model

data class MovieDetailsModel(
    val runtime: Int,
    val overview: String,
    val original_title: String,
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String
)
