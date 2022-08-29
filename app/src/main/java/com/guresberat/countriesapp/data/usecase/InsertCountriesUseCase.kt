package com.guresberat.countriesapp.data.usecase

import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.data.repository.LocalRepositoryImpl
import javax.inject.Inject

class InsertCountriesUseCase @Inject constructor(private val localRepository: LocalRepositoryImpl) {

    suspend operator fun invoke(country: Country) = localRepository.insert(country)
}