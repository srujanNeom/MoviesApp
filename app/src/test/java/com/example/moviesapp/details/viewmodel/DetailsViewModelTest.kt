package com.example.moviesapp.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviesapp.details.domain.MovieDetailsResult
import com.example.moviesapp.details.domain.MovieDetailsUseCase
import com.example.moviesapp.details.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var movieInfo: MovieDetails

    @Mock
    private lateinit var detailsUseCase: MovieDetailsUseCase

    @Mock
    private lateinit var movieDetailsObserver: Observer<MovieDetails>

    @Mock
    private lateinit var errorObserver: Observer<Boolean>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    private lateinit var subject: MovieDetailsViewModel

    private val movieDetails: MediatorLiveData<MovieDetails> = MediatorLiveData()
    private val error: MediatorLiveData<Boolean> = MediatorLiveData()
    private val loader: MediatorLiveData<Boolean> = MediatorLiveData()
    private val fetchMovieDetailsUseCaseLiveData: MutableLiveData<MovieDetailsResult> =
        MutableLiveData()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        setUpLiveData()
        subject = MovieDetailsViewModel(detailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun setUpLiveData() {
        movieDetails.observeForever(movieDetailsObserver)
        error.observeForever(errorObserver)
        loader.observeForever(loadingObserver)
        BDDMockito.given(detailsUseCase.getLiveData()).willReturn(fetchMovieDetailsUseCaseLiveData)
    }

    @Test
    fun `fetchMovies - execute`() {
        whenMemesAreFetched()
        thenObserverShouldReceiveCorrectStates(loadingObserver)
        suspend { thenUseCaseShouldBeExecuted() }
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        BDDMockito.then(detailsUseCase).should().getLiveData()
    }

    private fun whenMemesAreFetched() {
        subject.fetchMovieDetails(23)
    }

    private fun thenObserverShouldReceiveCorrectStates(loading: Observer<Boolean>) {
        BDDMockito.then(loading).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveMoviesStates(data: Observer<MovieDetails>) {
        BDDMockito.then(data).shouldHaveNoMoreInteractions()
    }

    private suspend fun thenUseCaseShouldBeExecuted() {
        detailsUseCase.getMovieDetails(23)
    }

    @Test
    fun `fetchMovies - success`() {
        whenFetchedMemesHasResult(MovieDetailsResult.OnSuccess(movieInfo))
        thenObserverShouldReceiveMoviesStates(movieDetailsObserver)
    }

    private fun whenFetchedMemesHasResult(result: MovieDetailsResult) {
        fetchMovieDetailsUseCaseLiveData.value = result
    }

    @Test
    fun `fetchMemes - error`() {
        whenFetchedMemesHasResult(MovieDetailsResult.OnError)
        thenObserverShouldReceiveCorrectStates(errorObserver)
    }

}