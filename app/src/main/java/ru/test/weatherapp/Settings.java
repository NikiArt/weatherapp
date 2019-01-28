package ru.test.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    private static Settings instance;
    private String city;
    private Boolean wetness = false;
    private Boolean windSpeed = false;
    private Boolean airPressure = false;
    private static List<WeatherValue> weatherHistory;


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
