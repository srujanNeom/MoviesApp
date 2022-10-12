package com.example.moviesapp.repository

import com.example.moviesapp.model.MoviesInfo
import com.example.moviesapp.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

abstract class HomeRepository {

    abstract suspend fun getAllMovies(): Response<MoviesInfo>

}

class HomeRepoImpl @Inject constructor(
) : HomeRepository() {
    override suspend fun getAllMovies(): Response<MoviesInfo> =
        RetrofitInstance.api.getPopularMovies()
}