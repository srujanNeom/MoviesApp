package com.example.moviesapp.details.di

import com.example.moviesapp.details.domain.MovieDetailsUseCaseImpl
import com.example.moviesapp.details.repository.MovieDetailsRepoImpl
import com.example.moviesapp.details.repository.MovieDetailsRepository
import com.example.moviesapp.details.domain.MovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    fun provideDetailsRepository(repository: MovieDetailsRepoImpl): MovieDetailsRepository =
        repository

    @Provides
    fun provideDetailsUseCase(useCase: MovieDetailsUseCaseImpl): MovieDetailsUseCase = useCase
}