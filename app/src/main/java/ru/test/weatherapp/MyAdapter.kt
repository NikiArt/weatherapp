package ru.test.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.text.SimpleDateFormat


class MyAdapter : RecyclerView.Adapter<ViewHolder>() {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Settings.instance().weatherHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.temperature.text.clear()
        holder.wetness.text.clear()
        holder.windSpeed.text.clear()
        holder.airPressure.text.clear()
        holder.cityName.text.clear()
        holder.date.text.clear()

        holder.temperature.text.append(Settings.instance().weatherHistory[position].temperature.toString() + " \u00B0C")
        holder.wetness.text.append("Влажность: ${Settings.instance().weatherHistory[position].wetness}%")
        holder.windSpeed.text.append("Скорость ветра: ${Settings.instance().weatherHistory[position].windSpeed} м/с")
        holder.airPressure.text.append("Давление: ${Settings.instance().weatherHistory[position].airPressure} мм рт.с.")
        holder.cityName.text.append(Settings.instance().weatherHistory[position].cityName)
        holder.date.text.append(Settings.instance().weatherHistory[position].currentDate)
    }
}


