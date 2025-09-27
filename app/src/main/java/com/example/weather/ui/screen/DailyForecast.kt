package com.example.weather.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DailyForecastScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "3-Day Forecast (Placeholder)",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Day 1
        ForecastItem(
            date = "Sept 23",
            condition = "Sunny",
            highTemp = "25°C",
            lowTemp = "16°C",
            precipitation = "0 mm ",
            wind = "S 9 kph",
            humidity = "60%"
        )

        // Day 2
        ForecastItem(
            date = "Sept 24",
            condition = "Cloudy",
            highTemp = "15°C",
            lowTemp = "10°C",
            precipitation = "2 mm ",
            wind = "NE 12 kph",
            humidity = "65%"
        )

        // Day 3
        ForecastItem(
            date = "Sept 25",
            condition = "Rain",
            highTemp = "17°C",
            lowTemp = "10°C",
            precipitation = "3 mm ",
            wind = "WE 12 kph",
            humidity = "80%"
        )
    }
}

@Composable
fun ForecastItem(
    date: String,
    condition: String,
    highTemp: String,
    lowTemp: String,
    precipitation: String,
    wind: String,
    humidity: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = date, style = MaterialTheme.typography.titleMedium)
            Text(text = condition, style = MaterialTheme.typography.bodyMedium)
            Text(text = "High: $highTemp / Low: $lowTemp")
            Text(text = "Precipitation: $precipitation")
            Text(text = "Wind: $wind")
            Text(text = "Humidity: $humidity")
        }
    }
}
