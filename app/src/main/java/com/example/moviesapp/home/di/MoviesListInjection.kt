package com.example.moviesapp.home.di

import com.example.moviesapp.home.domain.MoviesListUseCase
import com.example.moviesapp.home.domain.MoviesListUseCaseImpl
import com.example.moviesapp.home.repository.MoviesListRepoImpl
import com.example.moviesapp.home.repository.MoviesListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideHomeRepository(repository: MoviesListRepoImpl): MoviesListRepository = repository

    @Provides
    fun provideHomeUseCase(useCase: MoviesListUseCaseImpl): MoviesListUseCase = useCase
}