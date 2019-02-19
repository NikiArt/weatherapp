package ru.test.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.test.weatherapp.parsingJson.WValue


interface WeatherApi {
    @GET("current.json")
    fun getWeather(@Query("key") apiKey: String, @Query("q") cityName: String): Call<WValue>
}