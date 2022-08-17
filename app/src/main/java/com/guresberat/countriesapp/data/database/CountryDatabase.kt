package com.guresberat.countriesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guresberat.countriesapp.data.dao.CountryDao
import com.guresberat.countriesapp.data.model.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountryDatabase : RoomDatabase(){
    abstract fun countryDao(): CountryDao

    companion object{
        const val DATABASE_NAME = "CountryDatabase"
    }
}