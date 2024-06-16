package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello and welcome! Type 'get exchange rate' to fetch the latest exchange rates.");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim(); // trim the input

            if ("get exchange rate".equalsIgnoreCase(input)) {
                String apiUrl = "https://v6.exchangerate-api.com/v6/84d0d9ef58a9a85b81a37e33/latest/USD";

                try {
                    URI uri = URI.create(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();

                    conn.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    // close connections
                    in.close();
                    conn.disconnect();

                    // Parse JSON response
                    JSONObject jsonResponse = new JSONObject(content.toString());
                    printExchangeRates(jsonResponse);

                } catch (Exception e) {
                    System.out.println("Sorry, an error occurred while fetching the exchange rates. Please try again later.");
                }
            } else {
                System.out.println("Unknown command. Please type 'get exchange rate' to fetch the latest exchange rates.");
            }
        }
    }

    private static void printExchangeRates(JSONObject jsonResponse) {
        JSONObject rates = jsonResponse.getJSONObject("conversion_rates");

        System.out.println("Here are the latest exchange rates for USD:");
        for (String key : rates.keySet()) {
            System.out.println(key + ": " + rates.getDouble(key));
        }
    }
}