package ru.test.weatherapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SettingsFragment.OnFragmentInteractionListener, ContentFragment.OnFragmentInteractionListener {
    val settingsFragment = SettingsFragment()
    val contentFragment = ContentFragment()
    private var currentFragment: Int = 1

    override fun onFragmentInteraction(currentFragment: Int) {
        changeFragment(currentFragment)
    }

    private fun changeFragment(currentFragment: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        if (currentFragment == 1) {
            fragmentTransaction.replace(R.id.content_main_frame, contentFragment)
            this.currentFragment = 2
        } else {
            fragmentTransaction.replace(R.id.content_main_frame, settingsFragment)
            this.currentFragment = 1
        }
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && currentFragment == 2) {
            changeFragment(currentFragment)
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }
}
