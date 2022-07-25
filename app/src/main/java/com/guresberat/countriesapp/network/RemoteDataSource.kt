package com.guresberat.countriesapp.network

import com.guresberat.countriesapp.data.model.Country
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("Country")
    suspend fun getCountries() : List<Country>
}