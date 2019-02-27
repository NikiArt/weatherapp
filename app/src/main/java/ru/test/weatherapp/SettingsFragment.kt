package ru.test.weatherapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_settings.view.*
import ru.test.weatherapp.geolocation.GeoLocation

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val geoLocation = GeoLocation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_settings, container, false)
        val buttonFromFragment = inflatedView.findViewById<View>(R.id.fragment_settings_button_get_weather)
        val buttonLocation = inflatedView.fragment_settings_button_current_location
        val cityName = inflatedView.findViewById<EditText>(R.id.fragment_settings_text_city)
        val airPressure = inflatedView.findViewById<Switch>(R.id.fragment_settings_switch_air_pressure)
        val wetness = inflatedView.findViewById<Switch>(R.id.fragment_settings_switch_wetness)
        val windSpeed = inflatedView.findViewById<Switch>(R.id.fragment_settings_switch_wind_speed)

        val pref = activity!!.getPreferences(Context.MODE_PRIVATE)
        cityName.setText(pref.getString("cityName", ""))
        wetness.isChecked = pref.getBoolean("wetness", true)
        airPressure.isChecked = pref.getBoolean("airPressure", true)
        windSpeed.isChecked = pref.getBoolean("windSpeed", true)

        buttonFromFragment.setOnClickListener {
            listener?.onFragmentInteraction(1, 2)
            if (cityName.text.toString().isEmpty()) {
                Toast.makeText(activity, "Укажите город, для которого Вы хотите узнать температуру", Toast.LENGTH_SHORT).show()
            } else {
                Settings.instance().city = cityName.text.toString()
                Settings.instance().airPressure = airPressure.isChecked
                Settings.instance().wetness = wetness.isChecked
                Settings.instance().windSpeed = windSpeed.isChecked
            }
        }

        buttonLocation.setOnClickListener {
            geoLocation.getLocation(activity ?: requireActivity())
        }
        return inflatedView

    }

    fun onButtonPressed() {
        listener?.onFragmentInteraction(1, 2)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(currentFragment: Int, nextFragment: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SettingsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
