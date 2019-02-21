package ru.test.weatherapp

import java.util.*

class WeatherValue(
        val temperature: Double?,
        var wetness: Int?,
        val airPressure: Double?,
        val windSpeed: Double?,
        val currentDate: Date,
        val cityName: String)