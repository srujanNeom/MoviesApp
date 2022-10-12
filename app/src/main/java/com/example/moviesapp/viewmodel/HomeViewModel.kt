package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.MoviesInfo
import com.example.moviesapp.repository.HomeRepository
import com.example.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesRepository: HomeRepository) :
    ViewModel() {

    private val _popularMovies = MutableLiveData<Resource<MoviesInfo>>()
    val popularMovies: LiveData<Resource<MoviesInfo>> = _popularMovies

    fun getPopularMovies() = viewModelScope.launch {
        _popularMovies.postValue(Resource.Loading())
        val response = moviesRepository.getAllMovies()
        handlePopularMoviesResponse(response)
    }

    private fun handlePopularMoviesResponse(response: Response<MoviesInfo>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                _popularMovies.postValue(Resource.Success(resultResponse))
            }
        } else {
            _popularMovies.postValue(Resource.Error(response.message()))
        }
    }
}