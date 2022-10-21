package com.example.moviesapp.home.model

import javax.inject.Inject

data class MoviesListModel @Inject constructor(
    val id: Int,
    val title: String,
    val poster_path: String
)
