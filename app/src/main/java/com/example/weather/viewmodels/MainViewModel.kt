package com.example.weather.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.WeatherResponse
import com.example.weather.services.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class MainViewModel : ViewModel() {

    var weatherState by mutableStateOf<WeatherState>(WeatherState.Loading)

    fun fetchWeather() {
        viewModelScope.launch {
            weatherState = WeatherState.Loading
            try {
                val response = RetrofitInstance.api.getForecast(
                    apiKey = "c611b4e1727d40c394320943251810",
                    city = "Halifax",
                    days = 3,
                    aqi = "no"
                )
                weatherState = WeatherState.Success(response)
            } catch (e: Exception) {
                weatherState = WeatherState.Error(e.message ?: "Unknown error")
            }
        }
    }
}