package com.example.moviesapp.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.home.domain.MoviesListResult
import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.domain.MoviesListUseCaseImpl
import com.example.moviesapp.home.repository.MoviesListRepository
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

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
    private lateinit var errorBody: ResponseBody

    private lateinit var subject: MoviesListUseCaseImpl

    @Before
    fun setUp() {
        subject = MoviesListUseCaseImpl(repository)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Response.success(moviesInfo)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MoviesListResult.OnSuccess(moviesInfo)
        thenLiveDataShouldHaveCorrectValue(MoviesListResult.OnSuccess(moviesInfo))
    }

    private suspend fun givenRepository(response: Response<MoviesInfo>) {
        BDDMockito.given(repository.getAllMovies()).willReturn(response)
    }

    private suspend fun whenUseCaseIsExecuted() {
        subject.fetchAllMovies()
    }

    private fun thenLiveDataShouldHaveCorrectValue(result: MoviesListResult) {
        assertEquals(subject.getLiveData().value, result)
    }

    @Test
    fun `execute - error`() {
        suspend { givenRepository(Response.error(201, errorBody)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MoviesListResult.OnError
        thenLiveDataShouldHaveCorrectValue(MoviesListResult.OnError)
    }
}