package com.example.weather.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.WeatherResponse
import com.example.weather.services.RetrofitInstance
import kotlinx.coroutines.launch

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class MainViewModel : ViewModel() {

    companion object {
        // 你的 key
        private const val API_KEY = "c611b4e1727d40c394320943251810"
        private const val DEFAULT_CITY = "Halifax"
    }

    var weatherState by mutableStateOf<WeatherState>(WeatherState.Loading)
        private set

    /**
     * coordinates 可以是:
     * - "Halifax"
     * - "44.65,-63.57"
     * 不传就 Halifax
     */
    fun fetchWeather(coordinates: String = DEFAULT_CITY) {
        viewModelScope.launch {
            weatherState = WeatherState.Loading
            try {
                val response = RetrofitInstance.api.getForecast(
                    apiKey = API_KEY,
                    q = coordinates      // ← 这里的名字要跟 WeatherService 一样
                )
                weatherState = WeatherState.Success(response)
            } catch (e: Exception) {
                weatherState = WeatherState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
