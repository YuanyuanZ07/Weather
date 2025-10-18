package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weather.ui.screen.CurrentWeatherScreen
import com.example.weather.ui.screen.DailyForecastScreen
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.utils.getWeatherIcon
import com.example.weather.viewmodels.MainViewModel
import com.example.weather.viewmodels.WeatherState

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                DisplayUI(mainViewModel)
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: Int) {
    object Current : Screen("current", "Now", R.drawable.icon_sunny)
    object Forecast : Screen("forecast", "Daily", R.drawable.icon_cloud)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    when (val weatherState = viewModel.weatherState) {
        is WeatherState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is WeatherState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = weatherState.message)
            }
        }
        is WeatherState.Success -> {
            val weatherResponse = weatherState.weather
            val navController = rememberNavController()
            val items = listOf(Screen.Current, Screen.Forecast)

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(weatherResponse.location.name) }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        items.forEach { screen ->
                            val iconRes = if (screen.route == "current") {
                                getWeatherIcon(weatherResponse.current.condition.text)
                            } else {
                                getWeatherIcon(weatherResponse.forecast.forecastday.first().day.condition.text)
                            }
                            NavigationBarItem(
                                icon = { Icon(painterResource(id = iconRes), contentDescription = screen.label) },
                                label = { Text(screen.label) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Current.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(route = Screen.Current.route) {
                        CurrentWeatherScreen(currentWeather = weatherResponse.current)
                    }
                    composable(route = Screen.Forecast.route) {
                        DailyForecastScreen(forecasts = weatherResponse.forecast.forecastday)
                    }
                }
            }
        }
    }
}
