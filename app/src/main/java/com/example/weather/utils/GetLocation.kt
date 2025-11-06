package com.example.weather.ui.utils

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.example.weather.viewmodels.MainViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocation(viewModel: MainViewModel) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.status.isGranted) {
        Log.i("TESTING", "Hurray, permission granted!")

        // 获取经纬度
        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if (ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val cancellationTokenSource = CancellationTokenSource()

            Log.i("TESTING", "Requesting location...")

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude.toString()
                    val lng = location.longitude.toString()
                    val coordinates = "$lat,$lng"

                    Log.i("TESTING", "Success: $coordinates")

                    // ✅ 调用 ViewModel 来加载天气
                    viewModel.fetchWeather(coordinates)
                } else {
                    Log.i("TESTING", "Problem encountered: Location returned null")
                    viewModel.fetchWeather("Halifax")
                }
            }
        }
    } else {
        // 第一次运行时弹出权限请求框
        LaunchedEffect(permissionState) {
            permissionState.launchPermissionRequest()
        }
    }
}
