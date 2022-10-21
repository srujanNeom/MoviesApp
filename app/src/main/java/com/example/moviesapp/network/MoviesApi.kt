package com.example.moviesapp.network

import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.utils.Constants.Companion.API_KEY
import com.example.moviesapp.utils.Constants.Companion.MOVIE_DETAILS_END_POINT
import com.example.moviesapp.utils.Constants.Companion.POPULAR_MOVIES_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET(POPULAR_MOVIES_END_POINT)
    suspend fun getPopularMovies(
        @Query("api_key")
        apikey: String = API_KEY,
        @Query("language")
        language: String = "en-US"
    ): Response<MoviesInfo>

    @GET(MOVIE_DETAILS_END_POINT)
    suspend fun getMovieDetails(
        @Path("ID")
        id: Int,
        @Query("api_key")
        apikey: String = API_KEY,
        @Query("language")
        language: String = "en-US"
    ): Response<MovieDetails>
}