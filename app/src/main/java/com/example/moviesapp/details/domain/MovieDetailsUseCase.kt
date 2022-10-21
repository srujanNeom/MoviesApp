package com.example.moviesapp.details.domain

import com.example.moviesapp.utils.UseCase

interface MovieDetailsUseCase : UseCase<MovieDetailsResult> {

    fun getMovieDetails(movieId: Int)
}
