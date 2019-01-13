package ru.test.weatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tagON";
    private String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView temperValue = findViewById(R.id.temperValue);
        final TextView wetness = findViewById(R.id.text_wetness_content_main);
        final TextView windSpeed = findViewById(R.id.text_wind_speed_content_main);
        final TextView airPressure = findViewById(R.id.text_air_pressure_content_main);
        final TextView textCity = findViewById(R.id.text_city_content_main);

        textCity.setText(Settings.instance().getCity());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        messageText = ((savedInstanceState == null) ? "Первый запуск!" : "Повторный запуск!") + " - onCreate()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeather(temperValue, wetness, windSpeed, airPressure);
            }
        });
        getWeather(temperValue, wetness, windSpeed, airPressure);
    }

    private void getWeather(TextView temperValue,
                            TextView wetness, TextView windSpeed, TextView airPressure) {
        int temperature = (int) (Math.random() * 30);
        String temperText = ((Math.random() > 0.5) ? "" : "-") + temperature + " \u00B0C";
        temperValue.setText(temperText);

        wetness.setVisibility(View.INVISIBLE);
        windSpeed.setVisibility(View.INVISIBLE);
        airPressure.setVisibility(View.INVISIBLE);

        if (Settings.instance().getAirPressure()) {
            airPressure.setVisibility(View.VISIBLE);
            String airPressureText = "Давление воздуха: " + ((int) (Math.random() * 1000)) + " мм рт. с.";
            airPressure.setText(airPressureText);
        }

        if (Settings.instance().getWetness()) {
            wetness.setVisibility(View.VISIBLE);
            String wetnessText = "Влажность: " + ((int) (Math.random() * 100)) + " %";
            wetness.setText(wetnessText);
        }

        if (Settings.instance().getWindSpeed()) {
            windSpeed.setVisibility(View.VISIBLE);
            String windSpeedText = "Скорость ветра: " + ((int) (Math.random() * 20)) + " м/с";
            windSpeed.setText(windSpeedText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        messageText = "onStart()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        messageText = "Повторный запуск!! - onRestoreInstanceState()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        messageText = "onResume()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        messageText = "onPause()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        messageText = "onSaveInstanceState()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onStop() {
        super.onStop();
        messageText = "onStop()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        messageText = "onRestart()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        messageText = "onDestroy()";
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_SHORT).show();
        Log.d(TAG, messageText);
    }
}
