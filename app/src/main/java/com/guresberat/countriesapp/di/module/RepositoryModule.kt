package com.guresberat.countriesapp.di.module

import com.guresberat.countriesapp.data.dao.CountryDao
import com.guresberat.countriesapp.data.repository.LocalRepository
import com.guresberat.countriesapp.data.repository.LocalRepositoryImpl
import com.guresberat.countriesapp.data.repository.RemoteRepository
import com.guresberat.countriesapp.data.repository.RemoteRepositoryImpl
import com.guresberat.countriesapp.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiDataStore: RemoteDataSource
    ): RemoteRepository = RemoteRepositoryImpl(apiDataStore)

    @Singleton
    @Provides
    fun provideLocalRepository(
        localRepository: CountryDao
    ): LocalRepository = LocalRepositoryImpl(localRepository)
}