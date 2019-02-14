package ru.test.weatherapp

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherRequest : AsyncTask<Unit, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg p0: Unit?) {


        val url = URL("http://api.apixu.com/v1/current.json?key=4fd10dd3f01a413cb3b94151191402&q=moscow") // Указать адрес URI
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "POST"
        urlConnection.readTimeout = 10000

        while (true) {

            urlConnection.connect()
            val response = BufferedReader(InputStreamReader(urlConnection.inputStream))
            urlConnection.disconnect()
            Log.d("DDLog", response.readText().toString())
            Thread.sleep(20000)
        }
        //return WeatherValue(0,0,0,0, Date(), "")

    }

    override fun onPostExecute(result: Unit) {
        super.onPostExecute(result)
    }
}