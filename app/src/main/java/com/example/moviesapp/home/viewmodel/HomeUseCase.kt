package com.example.moviesapp.home.viewmodel

import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.repository.HomeRepository
import com.example.moviesapp.utils.UseCase
import com.example.moviesapp.home.viewmodel.HomeUseCase.Result
import com.example.moviesapp.utils.BaseUseCase
import javax.inject.Inject

interface HomeUseCase : UseCase<Result> {

    sealed class Result {
        data class OnSuccess(val moviesInfo: MoviesInfo) : Result()
        object OnError : Result()
    }

    suspend fun fetchAllMovies()
}

class HomeUseCaseImpl @Inject constructor(private val homeRepo: HomeRepository) :
    BaseUseCase<Result>(), HomeUseCase {

    override suspend fun fetchAllMovies() {
        val response = homeRepo.getAllMovies()
        if (response.isSuccessful) {
            response.body()?.let { moviesResponse ->
                liveData.value = Result.OnSuccess(moviesResponse)
            }
        } else {
            liveData.value = Result.OnError
        }
    }
}