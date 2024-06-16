package org.example;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherService {
    private static final String API_KEY = "bffd6324f23acbff254040085c9452f5";
    private static final String API_ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?q=Tbilisi,ge&appid=" + API_KEY;
    private static final Logger LOGGER = Logger.getLogger(WeatherService.class.getName());

    public String getWeather() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            double tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp");
            double tempInCelsius = tempInKelvin - 273.15;
            return String.format("%.2f", tempInCelsius);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return "Error: " + e.getMessage();
        }
    }
}