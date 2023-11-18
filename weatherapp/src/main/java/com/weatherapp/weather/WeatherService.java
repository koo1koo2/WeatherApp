package com.weatherapp.weather;
import java.util.ArrayList;
import java.util.LinkedList;
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
    
    public ArrayList<WeatherData> getWeatherForecast(String weatherDataString) {
       
        //Create an empty list
        
        ArrayList<WeatherData> forecastDaysList = new ArrayList<WeatherData>();

        // Create a JSON object from the String
        JSONObject jsonObject = new JSONObject(weatherDataString);

        JSONObject forecastObject = jsonObject.getJSONObject("forecast");
        JSONArray forecastDaysArray = forecastObject.getJSONArray("forecastday");

        // Iterate through forecast days
        for (int i = 0; i < forecastDaysArray.length(); i++) {

            JSONObject forecastDayObject = forecastDaysArray.getJSONObject(i);
            String date = forecastDayObject.getString("date");
            JSONObject dayObject = forecastDayObject.getJSONObject("day");
            String temperatureMax = String.valueOf(dayObject.getFloat("maxtemp_c"));
            String temperatureMin = String.valueOf(dayObject.getFloat("mintemp_c"));
            String teperatureAvg = String.valueOf(dayObject.getFloat("avgtemp_c"));
            String windSpeed = String.valueOf(dayObject.getFloat("maxwind_kph"));
            String totalPrecip = String.valueOf(dayObject.getFloat("totalprecip_mm"));
            String totalSnow = String.valueOf(dayObject.getFloat("totalsnow_cm"));
            String chanceOfRain = String.valueOf(dayObject.getInt("daily_chance_of_rain"));
            String chanceOfSnow = String.valueOf(dayObject.getInt("daily_chance_of_snow"));
            JSONObject conditionObject = dayObject.getJSONObject("condition");
            String condition = conditionObject.getString("text");

            //Construnct a weatherdata instance and add it to the list
            WeatherData forecastDay = new WeatherData(temperatureMax, temperatureMin, teperatureAvg, windSpeed, chanceOfRain, chanceOfSnow, totalSnow, totalPrecip, condition, date);
            forecastDaysList.add(forecastDay);
        }

        return forecastDaysList;

    }
}
