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
        WeatherService weatherService = new WeatherService();
        MainWindow mainWindow = new MainWindow(weatherService);

        // Create a scene and set it on the stage
        Scene scene = new Scene(mainWindow, 800, 600); // Adjust the size accordingly
        primaryStage.setScene(scene);

        // Set stage properties
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }
}



