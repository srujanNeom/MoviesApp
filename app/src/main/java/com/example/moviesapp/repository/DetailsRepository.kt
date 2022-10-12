package com.example.moviesapp.repository

import com.example.moviesapp.model.MovieDetails
import com.example.moviesapp.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

abstract class DetailsRepository {

    abstract suspend fun getMovieDetails(movieId: Any): Response<MovieDetails>

}

class DetailsRepoImpl @Inject constructor(
) : DetailsRepository() {

    override suspend fun getMovieDetails(movieId: Any): Response<MovieDetails> =
        RetrofitInstance.api.getMovieDetails(id = movieId)
}