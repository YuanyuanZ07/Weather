package com.example.weather.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyForecast(
    val day: String,
    val condition: String,
    val high: Int,
    val low: Int
) : Parcelable