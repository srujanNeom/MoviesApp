package com.example.moviesapp.details.di

import com.example.moviesapp.details.repository.DetailsRepoImpl
import com.example.moviesapp.details.repository.DetailsRepository
import com.example.moviesapp.details.viewmodel.DetailsUseCase
import com.example.moviesapp.details.viewmodel.DetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DetailsModule {

    @Provides
    fun provideDetailsRepository(repository: DetailsRepoImpl): DetailsRepository = repository

    @Provides
    fun provideDetailsUseCase(useCase: DetailsUseCaseImpl): DetailsUseCase = useCase
}