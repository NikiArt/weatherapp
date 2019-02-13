package ru.test.weatherapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SettingsFragment.OnFragmentInteractionListener, ContentFragment.OnFragmentInteractionListener, HistoryFragment.OnFragmentInteractionListener {
    val settingsFragment = SettingsFragment()
    val contentFragment = ContentFragment()
    val historyFragment = HistoryFragment()
    private var currentFragment: Int = 1
    private var previousFragment: Int = 1

    override fun onFragmentInteraction(currentFragment: Int, nextFragment: Int) {
        changeFragment(currentFragment, nextFragment)
    }

    private fun changeFragment(currentFragment: Int, nextFragment: Int) {
        this.previousFragment = currentFragment
        this.currentFragment = nextFragment
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_main_frame, getCurrentFragment(nextFragment))
        fragmentTransaction.commit()
    }

    private fun getCurrentFragment(fragmentNumber: Int): Fragment {
        return when (fragmentNumber) {
            1 -> settingsFragment
            2 -> contentFragment
            3 -> historyFragment
            else -> settingsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_main_frame, settingsFragment)
        fragmentTransaction.commit()


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        item.isChecked = true
        when (item.itemId) {
            R.id.about_dev -> {

            }
            R.id.write_me -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && currentFragment != 1) {
            changeFragment(previousFragment - 1, previousFragment)
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onDestroy() {
        val editor = getPreferences(Context.MODE_PRIVATE).edit()
        editor.putString("cityName", Settings.instance().city)
        editor.putBoolean("wetness", Settings.instance().wetness)
        editor.putBoolean("airPressure", Settings.instance().airPressure)
        editor.putBoolean("windSpeed", Settings.instance().windSpeed)
        editor.apply()
        super.onDestroy()
    }
}
