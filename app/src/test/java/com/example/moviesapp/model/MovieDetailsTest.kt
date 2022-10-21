package com.example.moviesapp.model

import com.example.moviesapp.details.model.MovieDetails
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieDetailsTest {

    @Test
    fun testModel() {
        val movieDetails = MovieDetails()
        val movieDetailsCopy = movieDetails.copy(adult = true)
        assertEquals(true, movieDetailsCopy.adult)
    }
}