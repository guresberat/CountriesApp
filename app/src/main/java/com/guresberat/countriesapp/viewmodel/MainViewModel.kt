package com.guresberat.countriesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.data.repository.MainRepository
import com.guresberat.countriesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val countriesSharedFlow: SharedFlow<Resource<List<Country>>>
        get() = _countriesSharedFlow

    private val _countriesSharedFlow = MutableSharedFlow<Resource<List<Country>>>()

    init {
        getDashboard()
    }

    private fun getDashboard() = viewModelScope.launch {
        _countriesSharedFlow.tryEmit(Resource.Loading)
        val countryList = mainRepository.getCountries()
        _countriesSharedFlow.tryEmit(Resource.Success(countryList))
    }
}