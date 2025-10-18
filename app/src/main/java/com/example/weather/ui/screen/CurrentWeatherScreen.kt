package com.example.weather.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.models.CurrentWeather
import com.example.weather.utils.getWeatherIcon

@Composable
fun CurrentWeatherScreen(currentWeather: CurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = getWeatherIcon(currentWeather.condition.text)),
                    contentDescription = currentWeather.condition.text,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = currentWeather.condition.text, style = MaterialTheme.typography.titleLarge)
                Text(text = "${currentWeather.temp_c}°C", style = MaterialTheme.typography.displayLarge)
                Text(text = "Feels like ${currentWeather.feelslike_c}°C")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Wind Speed")
                    Text(text = "${currentWeather.wind_kph} km/h")
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Wind Direction")
                    Text(text = currentWeather.wind_dir)
                }
            }
        }
    }
}