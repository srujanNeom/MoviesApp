package com.example.moviesapp.model

import com.example.moviesapp.details.model.ProductionCompany
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ProductionCompanyTest {

    @Test
    fun testModel() {
        val productionCompany = ProductionCompany()
        val productionCompanyCopy = productionCompany.copy(id = 12)
        assertEquals(12, productionCompanyCopy.id)
    }
}