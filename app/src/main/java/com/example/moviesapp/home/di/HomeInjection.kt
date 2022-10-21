package com.example.moviesapp.home.di

import com.example.moviesapp.home.repository.HomeRepoImpl
import com.example.moviesapp.home.repository.HomeRepository
import com.example.moviesapp.home.viewmodel.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideHomeRepository(repository: HomeRepoImpl): HomeRepository = repository

    @Provides
    fun provideHomeUseCase(useCase: HomeUseCaseImpl): HomeUseCase = useCase
}