package com.example.moviesapp.home.repository

import com.example.moviesapp.home.model.MoviesInfo
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