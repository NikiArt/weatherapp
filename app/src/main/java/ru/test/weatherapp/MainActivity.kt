package ru.test.weatherapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SettingsFragment.OnFragmentInteractionListener, ContentFragment.OnFragmentInteractionListener {
    val settingsFragment = SettingsFragment()
    val contentFragment = ContentFragment()

    override fun onFragmentInteraction(currentFragment: Int) {
        /* fragmentTransaction.remove(settingsFragment)
         fragmentTransaction.commit()
         fragmentTransaction.add(R.id.fragment, contentFragment)
         fragmentTransaction.commit() */
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_main_frame, if (currentFragment == 1) contentFragment else settingsFragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_main_frame, settingsFragment)
        fragmentTransaction.commit()


    }


}
