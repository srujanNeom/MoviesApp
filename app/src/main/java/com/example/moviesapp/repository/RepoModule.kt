package com.example.moviesapp.repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun detailsRepo(detailsRepoImpl: DetailsRepoImpl): DetailsRepository = detailsRepoImpl

    @Provides
    fun homeRepo(homeRepoImpl: HomeRepoImpl): HomeRepository = homeRepoImpl
}