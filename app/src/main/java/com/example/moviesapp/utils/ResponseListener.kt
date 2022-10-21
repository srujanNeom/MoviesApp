package com.example.moviesapp.utils

interface ResponseListener<T> {

    fun onResponse(response: T)

    fun onFailure(message: String)

}