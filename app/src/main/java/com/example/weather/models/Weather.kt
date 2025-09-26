package com.example.weather.models

import android.health.connect.datatypes.units.Temperature

data class Weather(
    val temperature: Double,
    val description: String
)