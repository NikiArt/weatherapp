package ru.test.weatherapp;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class App extends Application {
    private static App instance;

    public static App instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        startService(new Intent(this, MainService.class));
        Log.i("DDLog", "startService(): Done!");
    }
}
