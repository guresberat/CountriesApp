package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.dao.CountryDao
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.data.model.CountryEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val localDataSource: CountryDao) :
    LocalRepository {
    override suspend fun insert(country: Country) {
        val countryEntity = country.id?.let {
            CountryEntity(
                id = it,
                name = country.name,
                area = country.area,
                desc = country.desc,
                url = country.url
            )
        }
        countryEntity?.let { localDataSource.insertCountry(it) }
    }

    override suspend fun getCountries(): List<Country> {
        val list = localDataSource.getCountries()
        val returnList = ArrayList<Country>()
        list.forEach {
            val country = Country(
                id = it.id,
                name = it.name,
                area = it.area,
                desc = it.desc,
                url = it.url
            )
            returnList.add(country)
        }
        return returnList
    }
}