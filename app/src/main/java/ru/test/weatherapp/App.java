package ru.test.weatherapp;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.test.weatherapp.database.DatabaseControl;

public class App extends Application {
    private static App instance;
    private static WeatherApi weatherApi;
    private DatabaseControl dataControl;
    private SQLiteDatabase database;

    public static App instance() {
        return instance;
    }

    public static WeatherApi getApi() {
        return weatherApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        startService(new Intent(this, MainService.class));
        createRetrofitInstance();
        dataControl = new DatabaseControl(this, "database.db", null, 1);
        database = dataControl.getWritableDatabase();
        Log.i("DDLog", "startService(): Done!");
    }

    private void createRetrofitInstance() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }

    public DatabaseControl getDataControl() {
        return dataControl;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
