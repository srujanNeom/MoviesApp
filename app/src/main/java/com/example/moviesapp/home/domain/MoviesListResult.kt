package com.example.moviesapp.home.domain

import com.example.moviesapp.home.model.MoviesListModel

sealed class MoviesListResult {
        data class OnSuccess(val moviesInfo: List<MoviesListModel>) : MoviesListResult()
        object OnError : MoviesListResult()
    }