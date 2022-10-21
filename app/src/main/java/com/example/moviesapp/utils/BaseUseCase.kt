package com.example.moviesapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseUseCase<T>(
    val liveData: MutableLiveData<T> = MutableLiveData()
) : UseCase<T> {

    override fun getLiveData(): LiveData<T> = liveData

}

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

}
