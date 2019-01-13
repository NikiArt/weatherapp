package ru.test.weatherapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    private val TAG = "tagON"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        var messageText = "onCreate StartActivity"
        val d = Log.d(TAG, messageText)
    }

    fun onClickEvent(view: View) {
        if (text_city_activity_start.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Укажите город, для которого Вы хотите узнать температуру", Toast.LENGTH_SHORT).show()
            return
        }
        Settings.instance().city = text_city_activity_start.text.toString()
        Settings.instance().airPressure = switch_air_pressure_activity_start.isChecked
        Settings.instance().wetness = switch_wetness_activity_start.isChecked
        Settings.instance().windSpeed = switch_wind_speed_activity_start.isChecked
        val intent = Intent(App.instance(), MainActivity::class.java)
        startActivity(intent)
    }

}
