package com.weatherapp.weather;
import java.time.LocalDate;

public class WeatherData {
    private int temperatureNow;
    private int temperatureMax;
    private int temperatureMin;
    private int temperatureAvg;
    private int windSpeed;
    private int windGusts;
    private int feelsLike;
    private float precipmm;
    private String conditions;
    private LocalDate date;

    //TODO Constructors
    //TODO getters
    public int getTemperatureNow() {
        return temperatureNow;
    }

    public int getTemperatureMax() {
        return temperatureMax;
    }

    public int getTemperatureMin() {
        return temperatureMin;
    }

    public int getTemperatureAvg() {
        return temperatureAvg;
    }

    //TODO setters 
}
