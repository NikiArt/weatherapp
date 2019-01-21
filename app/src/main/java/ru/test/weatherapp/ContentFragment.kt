package ru.test.weatherapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class ContentFragment : Fragment() {

    private lateinit var mParam1: String
    private lateinit var mParam2: String
    lateinit var cityName: TextView
    lateinit var airPressure: TextView
    lateinit var wetness: TextView
    lateinit var windSpeed: TextView
    lateinit var temperValue: TextView

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_content, container, false)
        val buttonFromFragment = inflatedView.findViewById<Button>(R.id.fragment_content_button)
        val backButtonFromFragment = inflatedView.findViewById<ImageButton>(R.id.fragment_content_button_return)
        cityName = inflatedView.findViewById(R.id.fragment_content_text_city)
        airPressure = inflatedView.findViewById(R.id.fragment_content_text_air_pressure)
        wetness = inflatedView.findViewById(R.id.fragment_content_text_wetness)
        windSpeed = inflatedView.findViewById(R.id.fragment_content_text_wind_speed)
        temperValue = inflatedView.findViewById(R.id.fragment_content_temperValue)

        cityName.text = Settings.instance().city
        getWeather()

        buttonFromFragment.setOnClickListener {
            getWeather()
        }

        backButtonFromFragment.setOnClickListener {
            mListener?.onFragmentInteraction(2)
        }

        return inflatedView
    }

    override fun onResume() {
        super.onResume()
    }


    fun onButtonPressed() {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(2)
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
        fun onFragmentInteraction(currentFragment: Int)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        // The request code must be 0 or greater.
        private val PLUS_ONE_REQUEST_CODE = 0

        fun newInstance(param1: String, param2: String): ContentFragment {
            val fragment = ContentFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun getWeather() {
        val temperature = (Math.random() * 30).toInt()
        val temperText = (if (Math.random() > 0.5) "" else "-") + temperature + " \u00B0C"
        temperValue.text = temperText

        wetness.visibility = View.INVISIBLE
        windSpeed.visibility = View.INVISIBLE
        airPressure.visibility = View.INVISIBLE

        if (Settings.instance().airPressure) {
            airPressure.visibility = View.VISIBLE
            val airPressureText = "Давление воздуха: " + (Math.random() * 1000).toInt() + " мм рт. с."
            airPressure.text = airPressureText
        }

        if (Settings.instance().wetness) {
            wetness.visibility = View.VISIBLE
            val wetnessText = "Влажность: " + (Math.random() * 100).toInt() + " %"
            wetness.text = wetnessText
        }

        if (Settings.instance().windSpeed) {
            windSpeed.visibility = View.VISIBLE
            val windSpeedText = "Скорость ветра: " + (Math.random() * 20).toInt() + " м/с"
            windSpeed.text = windSpeedText
        }
    }

}
