package com.example.weather.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val temp_c: Double,
    val feelslike_c: Double,
    val wind_kph: Double,
    val wind_dir: String,
    val condition: Condition
) : Parcelable
