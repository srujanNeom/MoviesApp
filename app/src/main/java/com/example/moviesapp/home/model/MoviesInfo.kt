package com.example.moviesapp.home.model


data class MoviesInfo(
    val page: Int? = null,
    val results: List<MovieInfoModel> = emptyList(),
    val total_pages: Int? = null,
    val total_results: Int? = null
)