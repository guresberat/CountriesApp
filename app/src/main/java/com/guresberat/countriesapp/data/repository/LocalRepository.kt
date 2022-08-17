package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.model.Country

interface LocalRepository {

    suspend fun insert(country: Country)

    suspend fun getCountries(): List<Country>
}