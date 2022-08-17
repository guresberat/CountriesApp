package com.guresberat.countriesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guresberat.countriesapp.data.model.Country
import com.guresberat.countriesapp.data.model.CountryEntity


@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Query("SELECT * FROM CountryDatabase")
    suspend fun getCountries() : List<CountryEntity>
}