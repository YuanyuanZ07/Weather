package com.example.weather.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val current: CurrentWeather,
    val forecast: List<DailyForecast>
) : Parcelable
