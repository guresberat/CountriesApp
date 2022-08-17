package com.guresberat.countriesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryDatabase")
data class CountryEntity(
    @PrimaryKey
    val id:String,
    val name:String?,
    val url:String?,
    val desc:String?,
    val area:String?,
)
