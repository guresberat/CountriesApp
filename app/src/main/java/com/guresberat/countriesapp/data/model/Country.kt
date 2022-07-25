package com.guresberat.countriesapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Country(
    val name:String?,
    val url:String?,
    val desc:String?,
    val area:String?,
    val id:String?,
) : Parcelable
