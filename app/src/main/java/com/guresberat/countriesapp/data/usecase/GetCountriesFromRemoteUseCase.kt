package com.guresberat.countriesapp.data.usecase

import com.guresberat.countriesapp.data.repository.RemoteRepository
import javax.inject.Inject

class GetCountriesFromRemoteUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.getCountries()
}