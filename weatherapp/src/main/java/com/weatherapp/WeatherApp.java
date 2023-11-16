package com.weatherapp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.weatherapp.gui.MainWindow;
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
        WeatherService weatherService = new WeatherService();
        MainWindow mainWindow = new MainWindow(weatherService);
        //TODO get location input
        //TODO get data string with calling weatherAPI.java with the location 
        //TODO get the weatherdata with the returned string from weatherservice.java
        //TODO show weatherdata in mainwindow by sending the weatherdata there
        // Create a scene and set it on the stage
        Scene scene = new Scene(mainWindow, 800, 600); // Adjust the size accordingly
        primaryStage.setScene(scene);

        // Set stage properties
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }
}



