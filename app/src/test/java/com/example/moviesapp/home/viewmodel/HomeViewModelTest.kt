package com.example.moviesapp.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviesapp.home.domain.MoviesListResult
import com.example.moviesapp.home.domain.MoviesListUseCase
import com.example.moviesapp.home.model.MoviesListModel
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
class HomeViewModelTest {
    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var homeUseCase: MoviesListUseCase

    @Mock
    private lateinit var moviesObserver: Observer<List<MoviesListModel>>

    @Mock
    private lateinit var moviesInfo: List<MoviesListModel>

    @Mock
    private lateinit var errorObserver: Observer<Boolean>

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    private lateinit var subject: MoviesListViewModel

    private val moviesData: MediatorLiveData<List<MoviesListModel>> = MediatorLiveData()
    private val error: MediatorLiveData<Boolean> = MediatorLiveData()
    private val loader: MediatorLiveData<Boolean> = MediatorLiveData()
    private val fetchMoviesUseCaseLiveData: MutableLiveData<MoviesListResult> = MutableLiveData()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        setUpLiveData()
        subject = MoviesListViewModel(homeUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun setUpLiveData() {
        moviesData.observeForever(moviesObserver)
        error.observeForever(errorObserver)
        loader.observeForever(loadingObserver)
        BDDMockito.given(homeUseCase.getLiveData()).willReturn(fetchMoviesUseCaseLiveData)
    }

    @Test
    fun `fetchMovies - execute`() {
        whenMemesAreFetched()
        thenObserverShouldReceiveCorrectStates(loadingObserver)
        suspend { thenUseCaseShouldBeExecuted() }
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        BDDMockito.then(homeUseCase).should().getLiveData()
    }

    private fun whenMemesAreFetched() {
        subject.fetchPopularMovies()
    }

    private fun thenObserverShouldReceiveCorrectStates(loading: Observer<Boolean>) {
        BDDMockito.then(loading).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveMoviesStates(data: Observer<List<MoviesListModel>>) {
        BDDMockito.then(data).shouldHaveNoMoreInteractions()
    }

    private suspend fun thenUseCaseShouldBeExecuted() {
        homeUseCase.fetchAllMovies()
    }

    @Test
    fun `fetchMovies - success`() {
        whenFetchedMemesHasResult(MoviesListResult.OnSuccess(moviesInfo))
        thenObserverShouldReceiveMoviesStates(moviesObserver)
    }

    private fun whenFetchedMemesHasResult(result: MoviesListResult) {
        fetchMoviesUseCaseLiveData.value = result
    }

    @Test
    fun `fetchMemes - error`() {
        whenFetchedMemesHasResult(MoviesListResult.OnError)
        thenObserverShouldReceiveCorrectStates(errorObserver)
    }

}