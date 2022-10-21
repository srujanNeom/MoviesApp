package com.example.moviesapp.home.viewmodel

import androidx.lifecycle.*
import com.example.moviesapp.home.domain.MoviesListUseCase
import com.example.moviesapp.home.domain.MoviesListResult
import com.example.moviesapp.home.model.MoviesListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val homeUseCase: MoviesListUseCase) :
    ViewModel() {

    val popularMovies = MediatorLiveData<List<MoviesListModel>>()
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

    private fun onFetchMovieResult(result: MoviesListResult?) {
        when (result) {
            is MoviesListResult.OnSuccess -> {
                popularMovies.value = result.moviesInfo
                showLoading.value = false
            }
            is MoviesListResult.OnError -> {
                noMoviesFound.value = true
                showLoading.value = false
            }
            else -> {
                showLoading.value = false
            }
        }
    }
}