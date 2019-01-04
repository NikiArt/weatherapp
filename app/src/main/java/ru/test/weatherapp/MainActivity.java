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
    private String weatherText;
    private String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView temperValue = findViewById(R.id.temperValue);
        final TextView weatherValue = findViewById(R.id.weatherValue);

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
                getWeather(temperValue, weatherValue);
            }
        });
        getWeather(temperValue, weatherValue);
    }

    private void getWeather(TextView temperValue, TextView weatherValue) {
        int temperature = (int) (Math.random() * 30);
        int weatherRnd = (int) (Math.random() * 7);
        String temperText = ((Math.random() > 0.5) ? "" : "-") + temperature;
        temperValue.setText(temperText);
        switch (weatherRnd) {
            case 1:
                weatherText = "Ясно";
                break;
            case 2:
                weatherText = "Пасмурно";
                break;
            case 3:
                weatherText = "Дождь";
                break;
            case 4:
                weatherText = "Ветер";
                break;
            case 5:
                weatherText = "Ураган";
                break;
            case 6:
                weatherText = "Снег";
                break;
            case 7:
                weatherText = "Апокалипсис";
                break;
        }
        weatherValue.setText(weatherText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
