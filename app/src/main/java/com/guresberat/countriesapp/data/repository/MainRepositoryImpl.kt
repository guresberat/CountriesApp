package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.network.RemoteDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : MainRepository {
    override suspend fun getCountries(): List<Country> {
        return remoteDataSource.getCountries()
    }
}