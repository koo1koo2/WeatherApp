package com.weatherapp.weather;


public class WeatherData {
    private String temperatureNow;
    private String temperatureMax;
    private String temperatureMin;
    private String temperatureAvg;
    private String windSpeed;
    private String windGusts;
    private String feelsLike;
    private String precipitation;
    private String conditions;
    private String date;
    private String lastUpdated;
    private String chanceOfRain;
    private String chanceOfSnow;
    private String totalSnow;
    private String totalPrecip;
    
    //Constructor for current weather
    public WeatherData(String temperatureNow, String windSpeed, String windGusts, String feelsLike, String precipitation, String conditions, String lastUpdated) {
        this.temperatureNow = temperatureNow;
        this.windSpeed = windSpeed;
        this.windGusts = windGusts;
        this.feelsLike = feelsLike;
        this.precipitation = precipitation;
        this.conditions = conditions;
        this.lastUpdated = lastUpdated;
    }
    //Costructor for weather forecast
    public WeatherData (String temperatureMax, String temperatureMin,
    String temperatureAvg, String windSpeed, String windGusts, String chanceOfRain, String chanceOfSnow,
    String totalSnow, String totalPrecip, String precipitation, String conditions, String date) {
        
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.temperatureAvg = temperatureAvg;
        this.precipitation = precipitation;
        this.conditions = conditions;
        this.date = date;
        this.chanceOfRain = chanceOfRain;
        this.chanceOfSnow = chanceOfSnow;
        this.totalSnow = totalSnow;
        this.totalPrecip = totalPrecip;
        
    }
    // Getters
    
   /*  public String getCurrentWeather() {

    }

    public String getWeatherForecast() {

    } */

    public String getChanceOfRain() {
        return chanceOfRain;
    }
    public String getChanceOfSnow() {
        return chanceOfSnow;
    }
    public String getTotalSnow() {
        return totalSnow;
    }
    public String getTotalPrecip() {
        return totalPrecip;
    }
    public String getTemperatureNow() {
        return temperatureNow;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getTemperatureAvg() {
        return temperatureAvg;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindGusts() {
        return windGusts;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getConditions() {
        return conditions;
    }

    public String getDate() {
        return date;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }


    
    //TODO setters 
}
