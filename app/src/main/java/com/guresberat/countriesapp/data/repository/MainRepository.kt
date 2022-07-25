package com.guresberat.countriesapp.data.repository

import com.guresberat.countriesapp.data.model.Country

interface MainRepository {

    suspend fun getCountries(): List<Country>
}