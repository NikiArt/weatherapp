package ru.test.weatherapp

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*


class MyAdapter() : RecyclerView.Adapter<ViewHolder>() {
    private val cities = mutableListOf("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург")

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //return Settings.instance().weatherHistory.size
        return cities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = cities[position]
        val lp = holder.textView.layoutParams as ConstraintLayout.LayoutParams
        holder.textView.layoutParams = lp

        //holder.textView.text = "1 " + Settings.instance().weatherHistory[position].temperature.toString()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView = view.item_text_view
    var constraintView = view.item_constraint_view
}
