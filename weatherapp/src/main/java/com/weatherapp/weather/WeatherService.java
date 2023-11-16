package com.weatherapp.weather;
import java.lang.reflect.Array;

import org.json.JSONArray;
import org.json.JSONObject;

//TODO Parse response string, return weatherdata instance
public class WeatherService{
        
    public WeatherData getCurrentWeatherData(String weatherDataString) {
        
        // Create a JSON object from the String
        JSONObject jsonObject = new JSONObject(weatherDataString);

        // Get the object that contains current weather data
        JSONObject currentWeatherObject = jsonObject.getJSONObject("current");

        // Get the elements
        String lastUpdated = currentWeatherObject.getString("last_updated");
        String temperatureNow = String.valueOf(currentWeatherObject.getFloat("temp_c"));
        String feelLike = String.valueOf(currentWeatherObject.getFloat( "feelslike_c"));
        String windSpeed = String.valueOf(currentWeatherObject.getFloat("wind_kph"));
        String windGusts = String.valueOf(currentWeatherObject.getFloat("gust_kph"));
        String precipitation = String.valueOf(currentWeatherObject.getFloat("precip_mm"));
        JSONObject conditionObject = currentWeatherObject.getJSONObject("condition");
        String conditionText = conditionObject.getString("text");

        // Construct a weatherdata instance
        WeatherData currentWeather = new WeatherData(temperatureNow, windSpeed, windGusts, feelLike, precipitation, conditionText, lastUpdated);
        return currentWeather;

    }
    
    public WeatherData[] getWeatherForecast(String weatherDataString) {
       //forloopba vhogy
        //TODO forecastday array index
        // Create a JSON object from the String
        JSONObject jsonObject = new JSONObject(weatherDataString);

        JSONObject forecastObject = jsonObject.getJSONObject("forecast");
        JSONArray forecastDaysArray = forecastObject.getJSONArray("forecastday");

    }
}
