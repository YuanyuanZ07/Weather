package com.example.weather.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Condition(
    val text: String,
    val icon: String
) : Parcelable