package com.example.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {
                WeatherApp(viewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun WeatherApp(viewModel: MainViewModel) {
    val context = LocalContext.current
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasLocationPermission = isGranted
        }
    )

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val cancellationTokenSource = CancellationTokenSource()
                fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                ).addOnSuccessListener { location ->
                    if (location != null) {
                        val coords = "${location.latitude},${location.longitude}"
                        Log.i("WEATHER_APP", "Location success: $coords")
                        viewModel.fetchWeather(coords)
                    } else {
                        Log.i("WEATHER_APP", "Location is null, fallback to default")
                        viewModel.fetchWeather()
                    }
                }.addOnFailureListener { e ->
                    Log.e("WEATHER_APP", "Location error: ${e.message}")
                    viewModel.fetchWeather()
                }
            }
        }
    }

    if (hasLocationPermission) {
        DisplayUI(viewModel = viewModel)
    } else {
        PermissionScreen { launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
    }
}

@Composable
fun PermissionScreen(onRequestPermission: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Location permission is required for this app.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRequestPermission) {
                Text("Grant Permission")
            }
        }
    }
}

sealed class Screen(val route: String, val label: String) {
    object Current : Screen("current", "Now")
    object Forecast : Screen("forecast", "Daily")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(viewModel: MainViewModel) {
    when (val state = viewModel.weatherState) {
        is WeatherState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is WeatherState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.message)
            }
        }

        is WeatherState.Success -> {
            val weatherResponse = state.weather
            val navController = rememberNavController()
            val tabs = listOf(Screen.Current, Screen.Forecast)

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = weatherResponse.location.name)
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        tabs.forEach { screen ->
                            val iconRes =
                                if (screen.route == Screen.Current.route) {
                                    getWeatherIcon(
                                        weatherResponse.current.condition.text
                                    )
                                } else {
                                    getWeatherIcon(
                                        weatherResponse.forecast.forecastday.first().day.condition.text
                                    )
                                }

                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = screen.label
                                    )
                                },
                                label = { Text(screen.label) },
                                selected = currentDestination
                                    ?.hierarchy
                                    ?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
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
                    composable(Screen.Current.route) {
                        CurrentWeatherScreen(
                            currentWeather = weatherResponse.current
                        )
                    }
                    composable(Screen.Forecast.route) {
                        DailyForecastScreen(
                            forecasts = weatherResponse.forecast.forecastday
                        )
                    }
                }
            }
        }
    }
}
