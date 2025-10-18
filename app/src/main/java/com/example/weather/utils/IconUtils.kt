package com.example.weather.utils

import com.example.weather.R

fun getWeatherIcon(condition: String): Int {
    return when {
        condition.contains("sunny", ignoreCase = true) -> R.drawable.icon_sunny
        condition.contains("clear", ignoreCase = true) -> R.drawable.icon_sunny
        condition.contains("cloud", ignoreCase = true) -> R.drawable.icon_cloud
        condition.contains("overcast", ignoreCase = true) -> R.drawable.icon_cloud
        condition.contains("mist", ignoreCase = true) -> R.drawable.icon_cloud
        condition.contains("rain", ignoreCase = true) -> R.drawable.icon_rainy
        condition.contains("drizzle", ignoreCase = true) -> R.drawable.icon_rainy
        condition.contains("sleet", ignoreCase = true) -> R.drawable.icon_rainy
        condition.contains("snow", ignoreCase = true) -> R.drawable.icon_rainy // Using rainy icon as a substitute
        condition.contains("thunder", ignoreCase = true) -> R.drawable.icon_rainy // Using rainy icon as a substitute
        else -> R.drawable.icon_sunny
    }
}