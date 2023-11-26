package com.example.ap_project;

public class WeatherManager {

    private String currentWeather;

    // Constructor
    public WeatherManager() {
        // Initialize with a default weather
        setDefaultWeather();
    }

    private void setDefaultWeather() {
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    // Setter for currentWeather (for testing purposes)
    public void setCurrentWeather(String weather) {
        this.currentWeather = weather;
    }
}
