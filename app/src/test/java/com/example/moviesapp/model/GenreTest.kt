package com.example.moviesapp.model

import com.example.moviesapp.details.model.Genre
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GenreTest {

    @Test
    fun testModel() {
        val genere = Genre()
        val genereCopy = genere.copy(id = 12)
        assertEquals(12, genereCopy.id)
    }
}