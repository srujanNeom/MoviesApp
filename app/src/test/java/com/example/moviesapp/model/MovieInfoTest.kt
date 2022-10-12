package com.example.moviesapp.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieInfoTest {

    @Test
    fun testModel() {
        val movieInfo = MoviesInfo()
        val movieInfoCopy = movieInfo.copy(page = 1)
        assertEquals(1, movieInfoCopy.page)
    }
}