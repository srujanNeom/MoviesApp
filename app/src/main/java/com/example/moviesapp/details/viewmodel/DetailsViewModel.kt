package com.example.moviesapp.details.viewmodel

import androidx.lifecycle.*
import com.example.moviesapp.details.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsUseCase: DetailsUseCase) :
    ViewModel() {

    val movieDetails = MediatorLiveData<MovieDetails>()
    val noMoviesFound = MediatorLiveData<Boolean>()
    val showLoading = MediatorLiveData<Boolean>()


    init {
        showLoading.value = true
        noMoviesFound.value = false
        movieDetails.addSource(detailsUseCase.getLiveData(), ::onFetchMovieDetails)
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            detailsUseCase.getMovieDetails(movieId)
        }
    }

    private fun onFetchMovieDetails(result: DetailsUseCase.Result?) {
        when (result) {
            is DetailsUseCase.Result.OnSuccess -> {
                movieDetails.value = result.moviesInfo
                showLoading.value = false
            }
            is DetailsUseCase.Result.OnError -> {
                noMoviesFound.value = true
                showLoading.value = false
            }
            else -> {
                showLoading.value = false
            }
        }
    }
}