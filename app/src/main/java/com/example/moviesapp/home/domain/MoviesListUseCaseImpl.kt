package com.example.moviesapp.home.domain

import com.example.moviesapp.home.model.MoviesListModel
import com.example.moviesapp.home.repository.MoviesListRepository
import com.example.moviesapp.utils.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesListUseCaseImpl @Inject constructor(
    private val moviesListRepo: MoviesListRepository,
    private val mapper: MoviesListMapper,
) :
    BaseUseCase<MoviesListResult>(), MoviesListUseCase {

    override fun fetchAllMovies() {
        moviesListRepo.getAllMovies()
            .map(mapper::map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::success, ::error)
            .track()
    }

    private fun success(movies: List<MoviesListModel>) {
        liveData.value = MoviesListResult.OnSuccess(movies)
    }

    private fun error(throwable: Throwable) {
        liveData.value = MoviesListResult.OnError
    }
}