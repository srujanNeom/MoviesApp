package com.example.moviesapp.home.repository

import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.network.MoviesApi
import retrofit2.Response
import javax.inject.Inject

abstract class MoviesListRepository {

    abstract suspend fun getAllMovies(): Response<MoviesInfo>

}

class MoviesListRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesListRepository() {
    override suspend fun getAllMovies(): Response<MoviesInfo> =
        moviesApi.getPopularMovies()
}