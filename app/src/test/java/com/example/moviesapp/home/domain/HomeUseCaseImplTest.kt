package com.example.moviesapp.home.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.model.MoviesListModel
import com.example.moviesapp.home.repository.MoviesListRepository
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeUseCaseImplTest {
    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MoviesListRepository

    @Mock
    private lateinit var moviesInfo: MoviesInfo

    @Mock
    lateinit var movies: List<MoviesListModel>

    private lateinit var subject: MoviesListUseCaseImpl

    @Mock
    private lateinit var throwable: Throwable

    @Mock
    private lateinit var mapper: MoviesListMapper

    @Before
    fun setUp() {
        subject = MoviesListUseCaseImpl(repository, mapper)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Single.just(moviesInfo)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MoviesListResult.OnSuccess(movies)
        thenLiveDataShouldHaveCorrectValue(MoviesListResult.OnSuccess(movies))
    }

    private fun givenRepository(single: Single<MoviesInfo>) {
        BDDMockito.given(repository.getAllMovies()).willReturn(single)
    }

    private fun whenUseCaseIsExecuted() {
        subject.fetchAllMovies()
    }

    private fun thenLiveDataShouldHaveCorrectValue(result: MoviesListResult) {
        assertEquals(subject.getLiveData().value, result)
    }

    @Test
    fun `execute - error`() {
        suspend { givenRepository(Single.error(throwable)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MoviesListResult.OnError
        thenLiveDataShouldHaveCorrectValue(MoviesListResult.OnError)
    }
}