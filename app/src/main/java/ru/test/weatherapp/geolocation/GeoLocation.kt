package ru.test.weatherapp.geolocation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import ru.test.weatherapp.App
import android.content.Context.LOCATION_SERVICE
import android.location.*
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import ru.test.weatherapp.R
import java.util.*




class GeoLocation() {
    lateinit var currentActivity: Activity
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var accuracy: String = "0"
    var cityName = ""

    lateinit var locationManager: LocationManager

    fun getLocation(currentActivity: Activity) {
        this.currentActivity = currentActivity
        if (ContextCompat.checkSelfPermission(App.instance(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(App.instance(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = currentActivity.getSystemService(LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_LOW
            val provider = locationManager.getBestProvider(criteria, true)
            if (provider != null) {
                locationManager.requestLocationUpdates(provider, 10000, 10f, object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        latitude = roundValue(location.latitude)
                        longitude = roundValue(location.longitude)
                        accuracy = java.lang.Float.toString(location.getAccuracy())
                        Toast.makeText(App.instance(), "latitude $latitude, longitude $longitude, accuracy $accuracy", Toast.LENGTH_LONG).show()
                        Log.d("DDLog", "latitude $latitude, longitude $longitude, accuracy $accuracy")
                        locationManager.removeUpdates(this)
                        getCity()
                    }

                    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                })
            }
        } else {
            /*if (ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {*/
                ActivityCompat.requestPermissions(currentActivity,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 10)
            //}
        }
    }


    fun getCity() {
        val geocoder = Geocoder(currentActivity, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses.size > 0 && addresses != null ) cityName = addresses[0].locality.toString()
        Log.d("DDLog", cityName)
        currentActivity.findViewById<EditText>(R.id.fragment_settings_text_city).setText(cityName)
    }

    fun roundValue(currValue: Double) : Double {
        var d = currValue * 1000000
        val i = Math.round(d).toInt()
        d = i.toDouble() / 1000000
        return d
    }
}