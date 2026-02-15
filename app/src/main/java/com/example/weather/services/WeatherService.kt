package com.example.weather.services

import com.example.weather.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // https://api.weatherapi.com/v1/forecast.json
    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,

        @Query("q") q: String,
        @Query("days") days: Int = 3,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): WeatherResponse
}
