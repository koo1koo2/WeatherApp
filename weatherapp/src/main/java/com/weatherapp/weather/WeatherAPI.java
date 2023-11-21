package com.weatherapp.weather;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

// Fetch weather data based on the location
// Return response string
public class WeatherAPI {
    private static final String API_KEY = "****";
    private static final String API_ENDPOINT = "http://api.weatherapi.com/";

    public String fetchWeatherData(String location) {

        try {
            // Build the API URL with the location and API key
            URI uri = new URI(API_ENDPOINT + "v1/forecast.json?key=" + API_KEY + "&q=" + location + "&days=3&aqi=no&alerts=no");
            URL url = uri.toURL();

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

             // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful 
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response 
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Convert stringbuilder to string
                String weatherDataString = response.toString();

                return weatherDataString;
            //handle exceptions
            } else {
                System.out.println("return code: " + responseCode);
                return null;
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            System.out.println("Stg went wrong");
            return null;
        }
        
    }

}
