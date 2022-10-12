package com.example.moviesapp.model

data class MovieInfoModel(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>? = emptyList(),
    val id: Int? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)