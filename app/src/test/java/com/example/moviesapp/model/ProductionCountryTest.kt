package com.example.moviesapp.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ProductionCountryTest {

    @Test
    fun testModel() {
        val productionCountry = ProductionCountry()
        val productionCountryCopy = productionCountry.copy(name = "India")
        assertEquals("India", productionCountryCopy.name)
    }
}