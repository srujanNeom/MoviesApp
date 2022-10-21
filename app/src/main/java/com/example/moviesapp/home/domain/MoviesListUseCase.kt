package com.example.moviesapp.home.domain

import com.example.moviesapp.utils.UseCase

interface MoviesListUseCase : UseCase<MoviesListResult> {

    fun fetchAllMovies()
}
