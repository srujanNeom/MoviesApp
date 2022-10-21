package com.example.moviesapp.home.viewmodel

import androidx.lifecycle.*
import com.example.moviesapp.home.model.MoviesInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) :
    ViewModel() {

    val popularMovies = MediatorLiveData<MoviesInfo>()
    val noMoviesFound = MediatorLiveData<Boolean>()
    val showLoading = MediatorLiveData<Boolean>()

    init {
        showLoading.value = true
        noMoviesFound.value = false
        popularMovies.addSource(homeUseCase.getLiveData(), ::onFetchMovieResult)
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            homeUseCase.fetchAllMovies()
        }
    }

    private fun onFetchMovieResult(result: HomeUseCase.Result?) {
        when (result) {
            is HomeUseCase.Result.OnSuccess -> {
                popularMovies.value = result.moviesInfo
                showLoading.value = false
            }
            is HomeUseCase.Result.OnError -> {
                noMoviesFound.value = true
                showLoading.value = false
            }
            else -> {
                showLoading.value = false
            }
        }
    }
}