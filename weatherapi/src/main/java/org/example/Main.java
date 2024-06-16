package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        WeatherService weatherService = new WeatherService();
        System.out.println("Weather in Tbilisi: " + weatherService.getWeather());
    }
}