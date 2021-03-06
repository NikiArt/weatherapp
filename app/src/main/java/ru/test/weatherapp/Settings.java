package ru.test.weatherapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Settings {
    private static Settings instance;
    private String city = "";
    private Boolean wetness = false;
    private Boolean windSpeed = false;
    private Boolean airPressure = false;
    private static String API_KEY = "4fd10dd3f01a413cb3b94151191402";
    private static List<WeatherValue> weatherHistory;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Settings() {
    }

    public static synchronized Settings instance() {
        if (instance == null) {
            instance = new Settings();
            weatherHistory = new ArrayList();
        }
        return instance;
    }

    public String getCity() {
        return city;
    }

    public SimpleDateFormat getDateFormat() {
        return sdf;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getWetness() {
        return wetness;
    }

    public void setWetness(Boolean wetness) {
        this.wetness = wetness;
    }

    public Boolean getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Boolean windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Boolean getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Boolean airPressure) {
        this.airPressure = airPressure;
    }

    public void addHistory(WeatherValue weatherValue) {
        weatherHistory.add(weatherValue);
    }

    public List<WeatherValue> getWeatherHistory() {
        return weatherHistory;
    }
}
