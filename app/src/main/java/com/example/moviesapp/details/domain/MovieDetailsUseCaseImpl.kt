package com.example.moviesapp.details.domain

import com.example.moviesapp.details.repository.MovieDetailsRepository
import com.example.moviesapp.utils.BaseUseCase
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(private val detailsRepo: MovieDetailsRepository) :
    BaseUseCase<MovieDetailsResult>(), MovieDetailsUseCase {

    override suspend fun getMovieDetails(movieId: Int) {
        val response = detailsRepo.getMovieDetails(movieId)
        if (response.isSuccessful) {
            response.body()?.let { moviesResponse ->
                liveData.value = MovieDetailsResult.OnSuccess(moviesResponse)
            }
        } else {
            liveData.value = MovieDetailsResult.OnError
        }
    }
}