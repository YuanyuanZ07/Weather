package com.example.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R

@Composable
fun DailyForecastScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "3-Day Forecast",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Day 1
        ForecastItem(
            date = "Sept 23",
            condition = "Sunny",
            iconRes = R.drawable.icon_sunny,
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
            iconRes = R.drawable.icon_cloud,
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
            iconRes = R.drawable.icon_rainy,
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
    iconRes: Int,
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
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = condition,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = date, style = MaterialTheme.typography.titleMedium)
                Text(text = condition, style = MaterialTheme.typography.bodyMedium)
                Text(text = "High: $highTemp / Low: $lowTemp")
                Text(text = "Precipitation: $precipitation")
                Text(text = "Wind: $wind")
                Text(text = "Humidity: $humidity")
            }
        }
    }
}
