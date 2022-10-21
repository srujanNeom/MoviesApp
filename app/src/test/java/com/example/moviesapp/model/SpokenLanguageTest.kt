package com.example.moviesapp.model

import com.example.moviesapp.details.model.SpokenLanguage
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SpokenLanguageTest {

    @Test
    fun testModel() {
        val spokenLanguage = SpokenLanguage()
        val spokenLanguageCopy = spokenLanguage.copy(english_name = "EN_US")
        assertEquals("EN_US", spokenLanguageCopy.english_name)
    }
}