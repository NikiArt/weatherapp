package ru.test.weatherapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_content.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.test.weatherapp.parsingJson.WValue
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.URL
import java.util.*

class ContentFragment : Fragment() {

    lateinit var cityName: TextView
    lateinit var airPressure: TextView
    lateinit var wetness: TextView
    lateinit var windSpeed: TextView
    lateinit var temperValue: TextView
    lateinit var imageView: ImageView
    lateinit var call: Call<WValue>

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_content, container, false)
        val buttonFromFragment = inflatedView.fragment_content_button
        val buttonHistory = inflatedView.fragment_content_button_history
        cityName = inflatedView.findViewById(R.id.fragment_content_text_city)
        airPressure = inflatedView.findViewById(R.id.fragment_content_text_air_pressure)
        wetness = inflatedView.findViewById(R.id.fragment_content_text_wetness)
        windSpeed = inflatedView.findViewById(R.id.fragment_content_text_wind_speed)
        temperValue = inflatedView.findViewById(R.id.fragment_content_temperValue)
        imageView = inflatedView.fragment_content_weather_image
        cityName.text = Settings.instance().city
        getWeather()

        buttonFromFragment.setOnClickListener {
            getWeather()
        }

        buttonHistory.setOnClickListener {
            mListener?.onFragmentInteraction(2, 3)
        }

        return inflatedView
    }

    override fun onResume() {
        super.onResume()
    }


    fun onButtonPressed() {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(2, 1)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(currentFragment: Int, nextFragment: Int)
    }

    companion object {
        private val PLUS_ONE_REQUEST_CODE = 0

        fun newInstance(param1: String, param2: String): ContentFragment {
            val fragment = ContentFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    public fun getWeather() {
        cityName.text = Settings.instance().city
        //var weatherValue: WeatherValue
        call = App.getApi().getWeather(Settings.instance().apiKey, Settings.instance().city)
        call.enqueue(object : Callback<WValue> {
            override fun onResponse(call: Call<WValue>, response: Response<WValue>) {
                val weatherValue = WeatherValue(
                        response.body()?.current?.tempC ?: 0.0,
                        response.body()?.current?.humidity ?: 0,
                        BigDecimal((response.body()?.current?.pressureMb
                                ?: 0.0) * 0.75).setScale(2, RoundingMode.HALF_EVEN).toDouble(),
                        BigDecimal((response.body()?.current?.windKph
                                ?: 0.0) / 3.6).setScale(2, RoundingMode.HALF_EVEN).toDouble(),
                        Date(),
                        response.body()?.location?.name ?: "")
                Settings.instance().addHistory(weatherValue)
                writeValues(weatherValue, response.body()?.current?.condition?.icon ?: "")
            }

            override fun onFailure(call: Call<WValue>, t: Throwable) {
                t.printStackTrace()
                Log.e("DDLog", "" + t.printStackTrace())
            }
        })
    }


    fun writeValues(currentWeather: WeatherValue, image: String) {
        Glide.with(this?.context ?: App.instance().baseContext)
                .load(URL("http:$image"))
                .into(imageView)

        temperValue.text = "${currentWeather.temperature} \u00B0C"

        wetness.visibility = View.INVISIBLE
        windSpeed.visibility = View.INVISIBLE
        airPressure.visibility = View.INVISIBLE

        if (Settings.instance().airPressure) {
            airPressure.visibility = View.VISIBLE
            val airPressureText = "Давление воздуха: ${currentWeather.airPressure} мм рт. с."
            airPressure.text = airPressureText
        }

        if (Settings.instance().wetness) {
            wetness.visibility = View.VISIBLE
            val wetnessText = "Влажность: ${currentWeather.wetness} %"
            wetness.text = wetnessText
        }

        if (Settings.instance().windSpeed) {
            windSpeed.visibility = View.VISIBLE
            val windSpeedText = "Скорость ветра: ${currentWeather.windSpeed} м/с"
            windSpeed.text = windSpeedText
        }
    }

    override fun onDestroy() {
        call.cancel()
        super.onDestroy()
    }
}

