package com.example.moviesapp.details.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.details.viewmodel.DetailsUseCase.Result
import com.example.moviesapp.details.repository.DetailsRepository
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
    private lateinit var repository: DetailsRepository

    @Mock
    private lateinit var movieDetails: MovieDetails

    @Mock
    private lateinit var errorBody: ResponseBody

    private lateinit var subject: DetailsUseCaseImpl

    @Before
    fun setUp() {
        subject = DetailsUseCaseImpl(repository)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Response.success(movieDetails)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = Result.OnSuccess(movieDetails)
        thenLiveDataShouldHaveCorrectValue(Result.OnSuccess(movieDetails))
    }

    private suspend fun givenRepository(response: Response<MovieDetails>) {
        BDDMockito.given(repository.getMovieDetails(23)).willReturn(response)
    }

    private suspend fun whenUseCaseIsExecuted() {
        subject.getMovieDetails(23)
    }

    private fun thenLiveDataShouldHaveCorrectValue(result: Result) {
        assertEquals(subject.getLiveData().value, result)
    }

    @Test
    fun `execute - error`() {
        suspend { givenRepository(Response.error(201, errorBody)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = Result.OnError
        thenLiveDataShouldHaveCorrectValue(Result.OnError)
    }

}