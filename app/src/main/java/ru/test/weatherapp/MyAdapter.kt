package ru.test.weatherapp

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView


class MyAdapter() : RecyclerView.Adapter<ViewHolder>(), Parcelable {


    constructor(parcel: Parcel) : this() {
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view as TextView)
    }

    override fun getItemCount(): Int {
        return Settings.instance().weatherHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = Settings.instance().weatherHistory[position].temperature.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyAdapter> {
        override fun createFromParcel(parcel: Parcel): MyAdapter {
            return MyAdapter(parcel)
        }

        override fun newArray(size: Int): Array<MyAdapter?> {
            return arrayOfNulls(size)
        }
    }

}

class ViewHolder(var textView: TextView) : RecyclerView.ViewHolder(textView) {
}