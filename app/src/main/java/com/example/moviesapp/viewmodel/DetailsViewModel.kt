package com.example.moviesapp.viewmodel

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
    val movieDetails: MutableLiveData<Resource<MovieDetails>> = MutableLiveData()

    fun getMovieDetails(movieId: Any) = viewModelScope.launch {
        movieDetails.postValue(Resource.Loading())
        val response = detailsRepo.getMovieDetails(movieId)
        movieDetails.postValue(handleMovieDetailsResponse(response))
    }

    private fun handleMovieDetailsResponse(response: Response<MovieDetails>): Resource<MovieDetails> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}