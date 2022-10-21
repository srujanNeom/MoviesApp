package com.example.moviesapp.details.domain

import com.example.moviesapp.details.model.MovieDetails

sealed class MovieDetailsResult {
    data class OnSuccess(val moviesInfo: MovieDetails) : MovieDetailsResult()
    object OnError : MovieDetailsResult()
}
