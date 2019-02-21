package ru.test.weatherapp.parsingJson

import com.google.gson.annotations.SerializedName

class Condition {

    @SerializedName("text")
    var text: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("code")
    var code: Int? = null

}