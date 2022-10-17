package com.example.moviesapp.repository

import com.example.moviesapp.model.MoviesInfo
import com.example.moviesapp.network.MoviesApi
import retrofit2.Response
import javax.inject.Inject

abstract class HomeRepository {

    abstract suspend fun getAllMovies(): Response<MoviesInfo>

}

class HomeRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : HomeRepository() {
    override suspend fun getAllMovies(): Response<MoviesInfo> =
        moviesApi.getPopularMovies()
}