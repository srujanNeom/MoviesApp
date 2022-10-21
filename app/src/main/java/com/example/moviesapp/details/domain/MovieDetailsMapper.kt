package com.example.moviesapp.details.domain

import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.details.model.MovieDetailsModel
import javax.inject.Inject

class MovieDetailsMapper @Inject constructor() {
    fun map(dtm: MovieDetails): MovieDetailsModel {
        return MovieDetailsModel(
            runtime = dtm.runtime,
            overview = dtm.overview,
            original_title = dtm.original_title,
            vote_average = dtm.vote_average,
            vote_count = dtm.vote_count,
            poster_path = dtm.poster_path
        )
    }
}