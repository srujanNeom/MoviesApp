package com.example.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.MovieDetails
import com.example.moviesapp.repository.DetailsRepository
import com.example.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepo: DetailsRepository) :
    ViewModel() {
    private val _movieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>> = _movieDetails

    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        _movieDetails.postValue(Resource.Loading())
        val response = detailsRepo.getMovieDetails(movieId)
        handleMovieDetailsResponse(response)
    }

    private fun handleMovieDetailsResponse(response: Response<MovieDetails>) {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                _movieDetails.postValue(Resource.Success(resultResponse))
            }
        } else {
            _movieDetails.postValue(Resource.Error(response.message()))
        }
    }
}