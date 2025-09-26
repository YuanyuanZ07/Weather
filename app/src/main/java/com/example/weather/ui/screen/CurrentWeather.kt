package com.example.weather.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrentWeatherScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Halifax, Nova Scotia", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        // 假图标，可以用 emoji 占位
        Text("☁", fontSize = 64.sp)

        Spacer(modifier = Modifier.height(8.dp))
        Text("Overcast", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(4.dp))
        Text("6°C", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))
        Text("Feels like 2°C")
        Text("Wind SW 18 kph")
    }
}
