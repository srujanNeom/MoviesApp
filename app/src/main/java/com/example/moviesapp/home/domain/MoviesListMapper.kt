package com.example.moviesapp.home.domain

import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.model.MoviesListModel

class MoviesListMapper {
    fun map(dtm: MoviesInfo): List<MoviesListModel> {
        return dtm.results.map {
            MoviesListModel(
                id = it.id,
                title = it.title,
                poster_path = it.poster_path,
            )
        }
    }
}