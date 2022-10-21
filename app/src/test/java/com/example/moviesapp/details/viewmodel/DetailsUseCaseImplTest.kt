package com.example.moviesapp.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.details.domain.MovieDetailsResult
import com.example.moviesapp.details.domain.MovieDetailsUseCaseImpl
import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.details.repository.MovieDetailsRepository
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
class DetailsUseCaseImplTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDetailsRepository

    @Mock
    private lateinit var movieDetails: MovieDetails

    @Mock
    private lateinit var errorBody: ResponseBody

    private lateinit var subject: MovieDetailsUseCaseImpl

    @Before
    fun setUp() {
        subject = MovieDetailsUseCaseImpl(repository)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Response.success(movieDetails)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MovieDetailsResult.OnSuccess(movieDetails)
        thenLiveDataShouldHaveCorrectValue(MovieDetailsResult.OnSuccess(movieDetails))
    }

    private suspend fun givenRepository(response: Response<MovieDetails>) {
        BDDMockito.given(repository.getMovieDetails(23)).willReturn(response)
    }

    private suspend fun whenUseCaseIsExecuted() {
        subject.getMovieDetails(23)
    }

    private fun thenLiveDataShouldHaveCorrectValue(result: MovieDetailsResult) {
        assertEquals(subject.getLiveData().value, result)
    }

    @Test
    fun `execute - error`() {
        suspend { givenRepository(Response.error(201, errorBody)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MovieDetailsResult.OnError
        thenLiveDataShouldHaveCorrectValue(MovieDetailsResult.OnError)
    }

}