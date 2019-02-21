package ru.test.weatherapp.parsingJson

import com.google.gson.annotations.SerializedName


class WValue {

    @SerializedName("location")
    var location: Location? = null
    @SerializedName("current")
    var current: Current? = null

    override fun toString(): String {
        return ("location ${location!!.name} current ${current!!.humidity}")
    }
}