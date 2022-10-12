package com.example.moviesapp.viewmodel

import MainCoroutineRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.repository.HomeRepoImpl
import com.example.moviesapp.repository.HomeRepository
import com.example.moviesapp.utils.Status
import getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var homeRepository: HomeRepository


    @Before
    fun setUp() {
        homeRepository = HomeRepoImpl()
        homeViewModel = HomeViewModel(homeRepository)
    }

    @Test
    fun shouldListenForLoadingResource() = runBlocking {
        homeViewModel.getPopularMovies()

        val value = homeViewModel.popularMovies.getOrAwaitValueTest()

        assertEquals(value.status, Status.LOADING)
    }
}