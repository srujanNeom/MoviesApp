package com.example.moviesapp.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.repository.DetailsRepoImpl
import com.example.moviesapp.repository.DetailsRepository
import com.example.moviesapp.utils.Status
import getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var detailsRepository: DetailsRepository

    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        detailsRepository = DetailsRepoImpl()
        detailsViewModel = DetailsViewModel(detailsRepository)
    }

    @Test
    fun shouldListenForLoadingResource() = runBlocking {
        detailsViewModel.getMovieDetails(23)

        val value = detailsViewModel.movieDetails.getOrAwaitValueTest()

        Assert.assertEquals(value.status, Status.LOADING)
    }
}