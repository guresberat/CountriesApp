package com.guresberat.countriesapp.data.usecase

import com.guresberat.countriesapp.data.repository.LocalRepositoryImpl
import javax.inject.Inject

class GetCountriesFromLocalUseCase @Inject constructor(private val localRepository: LocalRepositoryImpl) {

    suspend operator fun invoke() = localRepository.getCountries()
}