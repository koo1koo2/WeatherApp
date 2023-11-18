package com.weatherapp.gui;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.LinkedList;

import com.weatherapp.weather.WeatherAPI;
import com.weatherapp.weather.WeatherData;
import com.weatherapp.weather.WeatherService;

import javafx.geometry.Insets;

public class MainWindow extends BorderPane {
    private TextField locationInput;
    private Button fetchButton;
    private Label currentWeatherLabel;
    private Label forecastLabel;
    private WeatherAPI weatherAPI;
    private WeatherService weatherService;

    public MainWindow(WeatherAPI weatherAPI, WeatherService weatherService) {
        // Initialize UI components
        this.weatherAPI = weatherAPI;
        this.weatherService = weatherService;
        locationInput = new TextField();
        fetchButton = new Button("Fetch Weather");
        currentWeatherLabel = new Label("Current Weather: ");
        forecastLabel = new Label("3-Day Forecast: ");

        // Set up event handler for the fetch button
        fetchButton.setOnAction(event -> fetchWeather());

        // Create layout panes for specific regions
        HBox topContainer = new HBox();
        VBox centerContainer = new VBox();

        // Add components to the layout panes
        topContainer.getChildren().addAll(locationInput, fetchButton);
        centerContainer.getChildren().addAll(currentWeatherLabel, forecastLabel);

        // Set up the BorderPane regions
        setTop(topContainer);
        setCenter(centerContainer);

        // Set layout properties (padding, spacing, etc.)
        setPadding(new Insets(10));
    }

    // Fetch weather using the WeatherService
    private void fetchWeather() {
        String location = locationInput.getText();
        String weatherDataString = weatherAPI.fetchWeatherData(location);
        WeatherData currentWeather = weatherService.getCurrentWeatherData(weatherDataString);
        ArrayList<WeatherData> forecastList = weatherService.getWeatherForecast(weatherDataString);

        // Update UI components with the fetched data
        currentWeatherLabel.setText("Current Weather: " + currentWeather.getConditions()+ " " + currentWeather.getTemperatureNow() + "°C " + "Feel like: " 
                                    +currentWeather.getFeelsLike() + "°C " + "Wind speed: " + currentWeather.getWindSpeed() + " kph " + "Wind gusts: " + 
                                    currentWeather.getWindGusts() + " kph " + "Precipitation: " + currentWeather.getPrecipitation() + " mm "  + "Last updated: " 
                                    + currentWeather.getLastUpdated());
        forecastLabel.setText("3-Day Forecast: \n" + forecastList.get(0).getForecast() + "\n" + forecastList.get(1).getForecast() + "\n" +
        forecastList.get(2).getForecast());
    }
}

