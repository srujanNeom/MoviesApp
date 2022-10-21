package com.example.moviesapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase<T>(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    val liveData: MutableLiveData<T> = MutableLiveData()
) : UseCase<T> {

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun getLiveData(): LiveData<T> = liveData

}

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

}
