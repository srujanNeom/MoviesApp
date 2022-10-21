package com.example.moviesapp.model

import com.example.moviesapp.home.model.MovieInfoModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieInfoModelTest {

    @Test
    fun testModel() {
        val movieInfoModel = MovieInfoModel()
        val movieInfoModelCopy = movieInfoModel.copy(adult = true)
        assertEquals(true, movieInfoModelCopy.adult)
    }
}