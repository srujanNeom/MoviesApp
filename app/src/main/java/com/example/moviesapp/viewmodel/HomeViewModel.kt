package com.example.moviesapp.viewmodel

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

    val popularMovies: MutableLiveData<Resource<MoviesInfo>> = MutableLiveData()

    fun getPopularMovies() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = moviesRepository.getAllMovies()
        popularMovies.postValue(handlePopularMoviesResponse(response))
    }

    private fun handlePopularMoviesResponse(response: Response<MoviesInfo>): Resource<MoviesInfo> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}