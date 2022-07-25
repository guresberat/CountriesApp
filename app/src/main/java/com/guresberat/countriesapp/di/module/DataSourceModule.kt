package com.guresberat.countriesapp.di.module

import com.google.gson.GsonBuilder
import com.guresberat.countriesapp.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        val gson = GsonBuilder()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://61d4260c8df81200178a8ae4.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))

        return retrofit.build().create(RemoteDataSource::class.java)
    }
}