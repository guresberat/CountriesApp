package com.guresberat.countriesapp.viewmodel

import android.content.SharedPreferences
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.data.repository.LocalRepository
import com.guresberat.countriesapp.data.repository.RemoteRepository
import com.guresberat.countriesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    val countriesSharedFlow: SharedFlow<Resource<List<Country>>>
        get() = _countriesSharedFlow

    private val _countriesSharedFlow = MutableSharedFlow<Resource<List<Country>>>()
    private val currentTime = SystemClock.currentThreadTimeMillis()
    private val difference = 30 * 60 * 1000


    private fun getFromLocal() = viewModelScope.launch {
        _countriesSharedFlow.emit(Resource.Loading)
        val countryList = remoteRepository.getCountries()
        _countriesSharedFlow.emit(Resource.Success(countryList))
    }

    private fun getFromApi() = viewModelScope.launch {
        val countryList = remoteRepository.getCountries()
        countryList.forEach {
            localRepository.insert(it)
        }
        getFromLocal()
    }

    fun fetchData(sharedPreference: SharedPreferences) {
        val time = sharedPreference.getLong("current-time", 0)
        if (time != 0.toLong()) {
            if (currentTime - time >= difference) {
                getFromApi()

            } else {
                getFromLocal()
            }
        } else {
            getFromApi()
            val editor = sharedPreference.edit()
            editor.putLong("current-time", currentTime)
            editor.apply()
        }
    }
}