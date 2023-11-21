package com.weatherapp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.weatherapp.gui.MainWindow;
import com.weatherapp.weather.WeatherAPI;
import com.weatherapp.weather.WeatherService;

public class WeatherApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize the main window
        // new weatherapi 
        // weatherapi string to call weatherservice method to get weatherdata
        WeatherAPI weatherAPI = new WeatherAPI();
        WeatherService weatherService = new WeatherService();
        MainWindow mainWindow = new MainWindow(weatherAPI, weatherService);
      
        
        // Create a scene and set it on the stage
        Scene scene = new Scene(mainWindow, 700, 350);
        primaryStage.setScene(scene);

        // Set stage properties
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }
}



