package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.model.Country

interface RemoteRepository {

    suspend fun getCountries(): List<Country>
}