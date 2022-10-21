package com.example.moviesapp.home.domain

import com.example.moviesapp.home.repository.MoviesListRepository
import com.example.moviesapp.utils.BaseUseCase
import javax.inject.Inject

class MoviesListUseCaseImpl @Inject constructor(private val moviesListRepo: MoviesListRepository) :
    BaseUseCase<MoviesListResult>(), MoviesListUseCase {

    override suspend fun fetchAllMovies() {
        val response = moviesListRepo.getAllMovies()
        if (response.isSuccessful) {
            response.body()?.let { moviesResponse ->
                liveData.value = MoviesListResult.OnSuccess(moviesResponse)
            }
        } else {
            liveData.value = MoviesListResult.OnError
        }
    }
}