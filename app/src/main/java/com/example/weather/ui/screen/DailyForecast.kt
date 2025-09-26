package com.example.weather.ui.screen

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
            .padding(16.dp)
    ) {
        Text("10-day forecast", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))
        Text("Sat, Sep 20 - 7°C / 2°C ☀")
        Text("Sun, Sep 21 - 8°C / 3°C 🌧")
        Text("Mon, Sep 22 - 6°C / 1°C ☁")
    }
}
