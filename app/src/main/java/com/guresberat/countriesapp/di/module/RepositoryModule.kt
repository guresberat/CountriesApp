package com.guresberat.countriesapp.di.module

import com.guresberat.countriesapp.data.repository.MainRepository
import com.guresberat.countriesapp.data.repository.MainRepositoryImpl
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
    fun provideMainRepository(
        apiDataStore: RemoteDataSource
    ): MainRepository = MainRepositoryImpl(apiDataStore)
}