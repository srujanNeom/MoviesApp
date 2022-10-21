package com.example.moviesapp.details.domain

import com.example.moviesapp.details.model.MovieDetailsModel

sealed class MovieDetailsResult {
    data class OnSuccess(val moviesInfo: MovieDetailsModel) : MovieDetailsResult()
    object OnError : MovieDetailsResult()
}
