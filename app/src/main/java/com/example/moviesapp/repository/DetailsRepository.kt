package com.example.moviesapp.repository

import com.example.moviesapp.model.MovieDetails
import com.example.moviesapp.network.MoviesApi
import retrofit2.Response
import javax.inject.Inject

abstract class DetailsRepository {

    abstract suspend fun getMovieDetails(movieId: Int): Response<MovieDetails>

}

class DetailsRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : DetailsRepository() {

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetails> =
        moviesApi.getMovieDetails(id = movieId)
}