package com.example.moviesapp.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.home.model.MoviesInfo
import com.example.moviesapp.home.viewmodel.HomeUseCase.Result
import com.example.moviesapp.home.repository.HomeRepository
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
    private lateinit var repository: HomeRepository

    @Mock
    private lateinit var moviesInfo: MoviesInfo

    @Mock
    private lateinit var errorBody: ResponseBody

    private lateinit var subject: HomeUseCaseImpl

    @Before
    fun setUp() {
        subject = HomeUseCaseImpl(repository)
    }

    @Test
    fun `execute - success`() {
        suspend { givenRepository(Response.success(moviesInfo)) }
        suspend { whenUseCaseIsExecuted() }
        subject.liveData.value = Result.OnSuccess(moviesInfo)
        thenLiveDataShouldHaveCorrectValue(Result.OnSuccess(moviesInfo))
    }

    private suspend fun givenRepository(response: Response<MoviesInfo>) {
        BDDMockito.given(repository.getAllMovies()).willReturn(response)
    }

    private suspend fun whenUseCaseIsExecuted() {
        subject.fetchAllMovies()
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