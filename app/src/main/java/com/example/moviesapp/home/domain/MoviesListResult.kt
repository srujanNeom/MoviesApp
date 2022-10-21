package com.example.moviesapp.home.domain

import com.example.moviesapp.home.model.MoviesInfo

sealed class MoviesListResult {
        data class OnSuccess(val moviesInfo: MoviesInfo) : MoviesListResult()
        object OnError : MoviesListResult()
    }