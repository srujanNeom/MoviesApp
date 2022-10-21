package com.example.moviesapp.details.domain

import com.example.moviesapp.details.model.MovieDetailsModel
import com.example.moviesapp.details.repository.MovieDetailsRepository
import com.example.moviesapp.utils.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(
    private val detailsRepo: MovieDetailsRepository,
    private val mapper: MovieDetailsMapper
) :
    BaseUseCase<MovieDetailsResult>(), MovieDetailsUseCase {

    override fun getMovieDetails(movieId: Int) {
        detailsRepo.getMovieDetails(movieId)
            .map(mapper::map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::success, ::error)
            .track()
    }

    private fun success(memes: MovieDetailsModel) {
        liveData.value = MovieDetailsResult.OnSuccess(memes)
    }

    private fun error(throwable: Throwable) {
        liveData.value = MovieDetailsResult.OnError
    }
}