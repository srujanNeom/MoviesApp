package com.example.moviesapp.details.repository

import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.network.MoviesApi
import retrofit2.Response
import javax.inject.Inject

abstract class MovieDetailsRepository {

    abstract suspend fun getMovieDetails(movieId: Int): Response<MovieDetails>

}

class MovieDetailsRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MovieDetailsRepository() {

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetails> =
        moviesApi.getMovieDetails(id = movieId)
}