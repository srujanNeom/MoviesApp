package com.example.moviesapp.utils

import com.example.moviesapp.BuildConfig

class Constants {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val MOVIES_BASE_URL = BuildConfig.MOVIES_BASE_URL
        const val POPULAR_MOVIES_END_POINT = "3/movie/popular"
        const val MOVIE_DETAILS_END_POINT = "3/movie/{ID}"
        const val IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL
    }
}