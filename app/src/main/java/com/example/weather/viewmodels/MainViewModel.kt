package com.example.weather.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.models.WeatherResponse
import com.example.weather.services.RetrofitInstance
import kotlinx.coroutines.launch

// App当前的天气数据状态
sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weather: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class MainViewModel : ViewModel() {

    // 供UI观察的状态（Compose会根据它重组界面）
    var weatherState by mutableStateOf<WeatherState>(WeatherState.Loading)

    /**
     * coordinates 可以是 "lat,lng" 或者城市名 "Halifax"
     */
    fun fetchWeather(coordinates: String = "Halifax") {
        viewModelScope.launch {
            weatherState = WeatherState.Loading
            try {
                // 调用 Retrofit
                val response = RetrofitInstance.api.getForecast(
                    apiKey = "c611b4e1727d40c394320943251810",
                    city = coordinates,
                    days = 3,
                    aqi = "no",
                    alerts = "no"
                )

                // 成功 -> 把数据放进 Success
                weatherState = WeatherState.Success(response)
            } catch (e: Exception) {
                // 失败 -> Error，UI会显示错误文本
                weatherState = WeatherState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
}
