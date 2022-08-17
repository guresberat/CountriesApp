package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.network.RemoteDataSource
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : RemoteRepository {
    override suspend fun getCountries(): List<Country> {
        return remoteDataSource.getCountries()
    }
}