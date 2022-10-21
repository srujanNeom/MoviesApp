package com.example.moviesapp.details.repository

import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.network.MoviesApi
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

abstract class MovieDetailsRepository {

    abstract fun getMovieDetails(movieId: Int): Single<MovieDetails>

}

class MovieDetailsRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MovieDetailsRepository() {

    override fun getMovieDetails(movieId: Int): Single<MovieDetails> =
        moviesApi.getMovieDetails(id = movieId)
}