package ru.test.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*


class MyAdapter() : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view as TextView)
    }

    override fun getItemCount(): Int {
        return Settings.instance().weatherHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = "1 " + Settings.instance().weatherHistory[position].temperature.toString()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView = view.textView
}
