package ru.test.weatherapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ru.test.weatherapp.App
import ru.test.weatherapp.Settings
import ru.test.weatherapp.WeatherValue
import java.util.*
import kotlin.collections.ArrayList

class DatabaseControl(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    companion object {
        const val DATABASE_NAME = "database.db"
        const val VERSION = 1

        const val CITIES_TABLE_NAME = "Cities"
        const val WEATHER_TABLE_NAME = "Weather"

        const val ID = "id"
        const val CITY = "city"
        const val TEMPERATURE = "temperature"
        const val WETNESS = "wetness"
        const val AIR_PRESSURE = "air_pressure"
        const val WIND_SPEED = "wind_speed"
        const val DATE = "date" //Date format: "YYYY-MM-DD HH:MM"
        const val IMAGE = "image"

        const val CREATE_CITIES_TABLE = "create table $CITIES_TABLE_NAME ( $ID TEXT primary key," +
                " $CITY TEXT NOT NULL)"
        const val CREATE_WEATHER_TABLE = "create table $WEATHER_TABLE_NAME ( $ID TEXT primary key," +
                " $CITY TEXT NOT NULL, $TEMPERATURE REAL, $WETNESS INTEGER, $AIR_PRESSURE REAL," +
                " $WIND_SPEED REAL, $IMAGE TEXT, $DATE TEXT NOT NULL)"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        Log.d("DDLog", "Start create CREATE_CITIES_TABLE")
        sqLiteDatabase.execSQL(CREATE_CITIES_TABLE)
        Log.d("DDLog", "Start create CREATE_CITIES_TABLE")
        sqLiteDatabase.execSQL(CREATE_WEATHER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addCity(city: String): String {
        val cityId = findCityId(city)
        return if (cityId == null) {
            val id = UUID.randomUUID().toString()
            val values = ContentValues()
            values.put(ID, id)
            values.put(CITY, city)
            App.instance().database.insert(CITIES_TABLE_NAME, null, values)
            Log.d("DDLog", "Create city in DB - $city")
            id
        } else {
            cityId
        }
    }

    fun addWeather(weatherValue: WeatherValue) {
        val currentCityId = addCity(weatherValue.cityName)
            val cursor = App.instance().database.query(WEATHER_TABLE_NAME, null, "$CITY = '$currentCityId'", null, null, null, "$DATE DESC")
            cursor.moveToFirst()
            if (cursor.isAfterLast ||
                    cursor.getDouble(2) != weatherValue.temperature ||
                    cursor.getInt(3) != weatherValue.wetness ||
                    cursor.getDouble(4) != weatherValue.airPressure ||
                    cursor.getDouble(5) != weatherValue.windSpeed) {
                addNewWeather(weatherValue)
            }
    }

    private fun addNewWeather(weatherValue: WeatherValue) {
        val values = ContentValues()
        values.put(ID, UUID.randomUUID().toString())
        values.put(CITY, addCity(weatherValue.cityName))
        values.put(TEMPERATURE, weatherValue.temperature)
        values.put(WETNESS, weatherValue.wetness)
        values.put(AIR_PRESSURE, weatherValue.airPressure)
        values.put(WIND_SPEED, weatherValue.windSpeed)
        values.put(DATE, weatherValue.currentDate)
        values.put(IMAGE, weatherValue.image)
        App.instance().database.insert(WEATHER_TABLE_NAME, null, values)
        Log.d("DDLog", "Create weather in DB - ${weatherValue.temperature} in ${weatherValue.cityName}")
    }

    fun getWeatherHystory(city: String) {
        val weatherHistory = ArrayList<WeatherValue>()
        val currentCityId = addCity(city)
        Settings.instance().weatherHistory.clear()
        val cursor = App.instance().database.query(WEATHER_TABLE_NAME, null, "$CITY = '$currentCityId'", null, null, null, "$DATE DESC")
        cursor.moveToFirst()
        if (!cursor.isAfterLast) {
            do {
                val weatherValue = WeatherValue(cursor.getDouble(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getString(7),
                        city,
                        cursor.getString(6))
                Settings.instance().weatherHistory.add(weatherValue)
            } while (cursor.moveToNext())
        }
    }


    fun findCityId(city: String): String? {
        val cursor = App.instance().database.query(CITIES_TABLE_NAME, null, "UPPER($CITY) = '${city.toUpperCase()}'", null, null, null, null)
        cursor.moveToFirst()
        return if (!cursor.isAfterLast) {
            cursor.getString(0)
        } else {
            null
        }
    }


}