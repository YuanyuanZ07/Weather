package com.example.weather.models

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition
)