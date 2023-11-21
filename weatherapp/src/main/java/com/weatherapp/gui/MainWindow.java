package com.weatherapp.gui;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

import com.weatherapp.weather.WeatherAPI;
import com.weatherapp.weather.WeatherData;
import com.weatherapp.weather.WeatherService;

import javafx.geometry.Insets;

public class MainWindow extends VBox {
    private TextField locationInput;
    private Button fetchButton;
    private Label currentWeatherLabel;
    private WeatherAPI weatherAPI;
    private WeatherService weatherService;
    private Label forecastLabel;
    private GridPane forecastGrid;
    private GridPane currentWeatherGrid;
    private Label inputLabel;
    
   
    

    public MainWindow(WeatherAPI weatherAPI, WeatherService weatherService) {
        // Initialize UI components
        this.weatherAPI = weatherAPI;
        this.weatherService = weatherService;
        inputLabel = new Label("Location");
        locationInput = new TextField("Vienna");
        fetchButton = new Button("Refresh");
        currentWeatherLabel = new Label("Current Weather");
        forecastLabel = new Label("3-Day Forecast");
        forecastGrid = new GridPane();
        currentWeatherGrid = new GridPane();


        //Show weather for the default location
        fetchWeather();

        // Set up event handlers 
        locationInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fetchWeather();
            }
        });
        fetchButton.setOnAction(event -> fetchWeather());

         
        // Create layout panes for specific regions
        HBox topContainer = new HBox();
        HBox weatherContainer = new HBox();
        VBox currentBox = new VBox();
        VBox forecastBox = new VBox();
        

        // Add components to the layout panes
        topContainer.getChildren().addAll(inputLabel, locationInput);
        currentBox.getChildren().addAll(currentWeatherLabel, currentWeatherGrid);
        forecastBox.getChildren().addAll(forecastLabel, forecastGrid);
        weatherContainer.getChildren().addAll(currentBox, forecastBox);
        
             

        // Create main layout
        getChildren().addAll(topContainer, weatherContainer, fetchButton);

        // Set layout properties (padding, spacing, etc.)
        weatherContainer.setSpacing(20);
        currentBox.setPadding(new Insets(10));
        forecastBox.setPadding(new Insets(10));
        forecastGrid.setHgap(20);
        forecastGrid.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        currentWeatherGrid.setHgap(20);
        currentWeatherGrid.setStyle("-fx-border-color: black; -fx-border-width: 1px;"); 
        setPadding(new Insets(10));
        setSpacing(20);
    }

    // Fetch weather using the WeatherService
    private void fetchWeather() {
        String location = locationInput.getText();
        String weatherDataString = weatherAPI.fetchWeatherData(location);
        WeatherData currentWeather = weatherService.getCurrentWeatherData(weatherDataString);
        ArrayList<WeatherData> forecastList = weatherService.getWeatherForecast(weatherDataString);
        
        // Check if fetching data was successfull
        if (weatherDataString != null && !weatherDataString.isEmpty()) {
            
            // Update current weather with the fetched data
            fillCurrentWeatherGrid(currentWeather);
            
            // Update forecast weather with data
            fillForecastGrid(forecastList);
        
        } else {
            //Display error message to user
            showAlert(AlertType.ERROR, "Error", "Could not get weather data", "Please check the location or try again later.");
        }
    }  
    
    // Method to fill currentWeatherGrid
    private void fillCurrentWeatherGrid(WeatherData currentWeather) {
        currentWeatherGrid.getChildren().clear();

        currentWeatherGrid.add(new Label("Temperature"),0,0);
        currentWeatherGrid.add(new Label("Feels Like"),0,1);
        currentWeatherGrid.add(new Label("Conditions"),0,2);
        currentWeatherGrid.add(new Label("Wind Speed"),0,3);
        currentWeatherGrid.add(new Label("Wind Gusts"),0,4);
        currentWeatherGrid.add(new Label("Precipitation"),0,5);
        currentWeatherGrid.add(new Label("Last Updated"),0,6);
        
        currentWeatherGrid.add(new Label(currentWeather.getTemperatureNow() + " °C"),1,0);
        currentWeatherGrid.add(new Label(currentWeather.getFeelsLike() + " °C"),1,1);
        currentWeatherGrid.add(new Label(currentWeather.getConditions()),1,2);
        currentWeatherGrid.add(new Label(currentWeather.getWindSpeed() + " kph"),1,3);
        currentWeatherGrid.add(new Label(currentWeather.getWindGusts() + " kph"),1,4);
        currentWeatherGrid.add(new Label(currentWeather.getPrecipitation() + " mm"),1,5);
        currentWeatherGrid.add(new Label(currentWeather.getLastUpdated()),1,6);

        
    }

   //Method to fill up forecastgrid
    private void fillForecastGrid(ArrayList<WeatherData> forecastList) {
        forecastGrid.getChildren().clear();

        forecastGrid.add(new Label("Date"), 0, 0);
        forecastGrid.add(new Label("Avg Temp"),0,1);
        forecastGrid.add(new Label("Max Temp"), 0, 2);
        forecastGrid.add(new Label("Min Temp"), 0, 3);
        forecastGrid.add(new Label("Conditions"),0,4);
        forecastGrid.add(new Label("Wind"), 0, 5);
        forecastGrid.add(new Label("Chance of Rain"),0,6);
        forecastGrid.add(new Label("Chance of Snow"),0,7);
        forecastGrid.add(new Label("Total Rain"),0,8);
        forecastGrid.add(new Label("Total Snow"), 0, 9);

        for(int i = 1 ; i <= forecastList.size(); i++) {

            WeatherData dayData = forecastList.get(i -1);

            forecastGrid.add(new Label(dayData.getDate()), i, 0);
            forecastGrid.add(new Label(dayData.getTemperatureAvg() + " °C"),i,1);
            forecastGrid.add(new Label(dayData.getTemperatureMax() + " °C"), i, 2);
            forecastGrid.add(new Label(dayData.getTemperatureMin() + " °C"), i, 3);
            forecastGrid.add(new Label(dayData.getConditions()),i,4);
            forecastGrid.add(new Label(dayData.getWindSpeed() + " kph "), i, 5);
            forecastGrid.add(new Label(dayData.getChanceOfRain() + " %"),i,6);
            forecastGrid.add(new Label(dayData.getChanceOfSnow() + " %"),i,7);
            forecastGrid.add(new Label(dayData.getTotalPrecip() + " mm"),i,8);
            forecastGrid.add(new Label(dayData.getTotalSnow() + " cm"), i, 9);

        }

        
    }

    //Method to alerd user
    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
    }

}

    

