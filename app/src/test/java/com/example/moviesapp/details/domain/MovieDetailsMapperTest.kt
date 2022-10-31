package com.example.moviesapp.details.domain

import com.example.moviesapp.details.model.*
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MovieDetailsMapperTest {

    private lateinit var dtm: MovieDetails

    private lateinit var subject: MovieDetailsMapper

    @Before
    fun setUp() {
        subject = MovieDetailsMapper()
    }

    @Test
    fun getMappedData() {
        // Create a task
        dtm = MovieDetails(
            adult = false,
            backdrop_path = "",
            belongs_to_collection = null,
            budget = 10000,
            genres = emptyList(),
            homepage = "",
            id = 1234,
            imdb_id = "",
            original_language = "en",
            original_title = "title",
            overview = "overview",
            popularity = 12.23,
            poster_path = "poster_path",
            production_companies = emptyList(),
            production_countries = emptyList(),
            release_date = "release_date",
            revenue = 1000,
            runtime = 38,
            spoken_languages = emptyList(),
            status = "",
            tagline = "tagline",
            title = "title",
            video = false,
            vote_average = 12.23,
            vote_count = 47,
        )

        val mappedData = MovieDetailsModel(
            runtime = dtm.runtime,
            overview = dtm.overview,
            original_title = dtm.original_title,
            vote_average = dtm.vote_average,
            vote_count = dtm.vote_count,
            poster_path = dtm.poster_path
        )

        // call the function
        val result = subject.map(dtm)

        // Check the result
        assertEquals(result, mappedData)
    }
}