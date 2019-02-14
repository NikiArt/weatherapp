package ru.test.weatherapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.text.SimpleDateFormat


class MainService : Service() {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val weatherrec = WeatherRequest()

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        weatherrec.execute()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}