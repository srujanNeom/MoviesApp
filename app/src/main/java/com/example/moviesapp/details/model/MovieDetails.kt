package com.example.moviesapp.details.model

import javax.inject.Inject


data class MovieDetails @Inject constructor(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = emptyList(),
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String,
    val overview: String,
    val popularity: Double? = null,
    val poster_path: String,
    val production_companies: List<ProductionCompany>? = emptyList(),
    val production_countries: List<ProductionCountry>? = emptyList(),
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val vote_average: Double,
    val vote_count: Int
)