package com.guresberat.countriesapp.di.module

import android.content.Context
import androidx.room.Room
import com.guresberat.countriesapp.data.dao.CountryDao
import com.guresberat.countriesapp.data.database.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideCountryDatabase(@ApplicationContext context: Context): CountryDatabase {
        return Room.databaseBuilder(
            context,
            CountryDatabase::class.java,
            CountryDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryDao(countryDatabase: CountryDatabase): CountryDao {
        return countryDatabase.countryDao()
    }
}