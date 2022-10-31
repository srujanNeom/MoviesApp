package com.example.moviesapp.home.domain

import com.example.moviesapp.home.model.MovieInfoModel
import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.model.MoviesListModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesListMapperTest {

    private lateinit var dtm: MoviesInfo

    private lateinit var subject: MoviesListMapper

    private lateinit var testData: List<MovieInfoModel>

    @Before
    fun setUp() {
        subject = MoviesListMapper()
    }

    @Test
    fun getMappedData() {
        // Create a task
        testData = listOf(
            MovieInfoModel(
                adult = false,
                backdrop_path = "",
                genre_ids = emptyList(),
                id = 1,
                original_language = "en",
                original_title = "original_title",
                overview = "overview",
                popularity = 76.54,
                poster_path = "poster_path",
                release_date = "release_date",
                title = "title",
                video = false,
                vote_average = 12.234,
                vote_count = 23
            ),
            MovieInfoModel(
                adult = false,
                backdrop_path = "",
                genre_ids = emptyList(),
                id = 2,
                original_language = "en",
                original_title = "original_title",
                overview = "overview",
                popularity = 76.54,
                poster_path = "poster_path",
                release_date = "release_date",
                title = "title",
                video = false,
                vote_average = 12.234,
                vote_count = 23
            )
        )

        val mappedData = listOf(
            MoviesListModel(
                id = testData[0].id,
                title = testData[0].title,
                poster_path = testData[0].poster_path
            ),
            MoviesListModel(
                id = testData[1].id,
                title = testData[1].title,
                poster_path = testData[1].poster_path
            ),
        )

        dtm = MoviesInfo(
            results = testData
        )
        // call the function
        val result = subject.map(dtm)

        // Check the result
        assertEquals(result, mappedData)
    }
}