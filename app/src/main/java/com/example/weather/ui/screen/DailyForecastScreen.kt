package com.example.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.models.ForecastDay
import com.example.weather.utils.getWeatherIcon

@Composable
fun DailyForecastScreen(forecasts: List<ForecastDay>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Text(
                text = "3-Day Forecast",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(forecasts) { forecast ->
            ForecastItem(forecast = forecast)
        }
    }
}

@Composable
fun ForecastItem(forecast: ForecastDay) {
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
                painter = painterResource(id = getWeatherIcon(forecast.day.condition.text)),
                contentDescription = forecast.day.condition.text,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = forecast.date, style = MaterialTheme.typography.titleMedium)
                Text(text = forecast.day.condition.text, style = MaterialTheme.typography.bodyMedium)
                Text(text = "High: ${forecast.day.maxtemp_c}°C / Low: ${forecast.day.mintemp_c}°C")
            }
        }
    }
}
