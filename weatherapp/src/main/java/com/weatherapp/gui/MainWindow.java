package com.weatherapp.gui;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
        VBox topContainer = new VBox();
        HBox centerContainer = new HBox();

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

        // Update UI components with the fetched data
        currentWeatherLabel.setText("Current Weather: " + currentWeather.getConditions());
        forecastLabel.setText("5-Day Forecast: " + location.length());
    }
}

