package com.example.moviesapp.home.repository

import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.network.MoviesApi
import io.reactivex.Single
import javax.inject.Inject

abstract class MoviesListRepository {

    abstract fun getAllMovies(): Single<MoviesInfo>

}

class MoviesListRepoImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesListRepository() {
    override fun getAllMovies(): Single<MoviesInfo> =
        moviesApi.getPopularMovies()
}