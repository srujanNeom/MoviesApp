package com.example.moviesapp.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BelongsToCollectionTest {

    @Test
    fun testModel() {
        val belongsToCollection = BelongsToCollection()
        val belongsToCollectionCopy = belongsToCollection.copy(poster_path = "/sample_image.jpg")
        assertEquals("/sample_image.jpg", belongsToCollectionCopy.poster_path)
    }
}