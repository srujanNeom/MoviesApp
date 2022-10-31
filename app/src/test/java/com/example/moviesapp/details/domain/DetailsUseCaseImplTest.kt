package com.example.moviesapp.details.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.details.model.MovieDetails
import com.example.moviesapp.details.model.MovieDetailsModel
import com.example.moviesapp.details.repository.MovieDetailsRepository
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
class DetailsUseCaseImplTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDetailsRepository

    @Mock
    private lateinit var movieDetails: MovieDetailsModel

    @Mock
    private lateinit var dto: MovieDetails

    private lateinit var subject: MovieDetailsUseCaseImpl

    @Mock
    private lateinit var throwable: Throwable

    @Mock
    private lateinit var mapper: MovieDetailsMapper

    @Before
    fun setUp() {
        subject = MovieDetailsUseCaseImpl(repository, mapper)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Single.just(dto)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MovieDetailsResult.OnSuccess(movieDetails)
        thenLiveDataShouldHaveCorrectValue(MovieDetailsResult.OnSuccess(movieDetails))
    }

    private fun givenRepository(single: Single<MovieDetails>) {
        BDDMockito.given(repository.getMovieDetails(23)).willReturn(single)
    }

    private fun whenUseCaseIsExecuted() {
        subject.getMovieDetails(23)
    }

    private fun thenLiveDataShouldHaveCorrectValue(result: MovieDetailsResult) {
        assertEquals(subject.getLiveData().value, result)
    }

    @Test
    fun `execute - error`() {
        suspend { givenRepository(Single.error(throwable)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = MovieDetailsResult.OnError
        thenLiveDataShouldHaveCorrectValue(MovieDetailsResult.OnError)
    }

}