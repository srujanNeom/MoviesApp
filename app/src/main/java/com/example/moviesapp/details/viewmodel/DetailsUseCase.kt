package com.example.moviesapp.details.viewmodel

import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.details.repository.DetailsRepository
import com.example.moviesapp.utils.BaseUseCase
import com.example.moviesapp.utils.UseCase
import javax.inject.Inject
import com.example.moviesapp.details.viewmodel.DetailsUseCase.Result


interface DetailsUseCase : UseCase<Result> {

    sealed class Result {
        data class OnSuccess(val moviesInfo: MovieDetails) : Result()
        object OnError : Result()
    }

    suspend fun getMovieDetails(movieId: Int)
}

class DetailsUseCaseImpl @Inject constructor(private val detailsRepo: DetailsRepository) :
    BaseUseCase<Result>(), DetailsUseCase {

    override suspend fun getMovieDetails(movieId: Int) {
        val response = detailsRepo.getMovieDetails(movieId)
        if (response.isSuccessful) {
            response.body()?.let { moviesResponse ->
                liveData.value = Result.OnSuccess(moviesResponse)
            }
        } else {
            liveData.value = Result.OnError
        }
    }
}