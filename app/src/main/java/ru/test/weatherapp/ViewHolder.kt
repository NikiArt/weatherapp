package ru.test.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.item.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var temperature: EditText = view.item_text_temperature
    var wetness: EditText = view.item_text_wetness
    var windSpeed: EditText = view.item_text_wind_speed
    var airPressure: EditText = view.item_text_air_pressure
    var cityName: EditText = view.item_text_city_name
    var date: EditText = view.item_text_date
}