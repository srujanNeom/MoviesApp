package com.example.moviesapp.details.domain

import com.example.moviesapp.utils.UseCase


interface MovieDetailsUseCase : UseCase<MovieDetailsResult> {


    suspend fun getMovieDetails(movieId: Int)
}
