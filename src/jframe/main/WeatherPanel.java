package jframe.main;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherPanel extends JPanel {
    public JLabel weatherLabel;
    public JLabel temperatureLabel;

    public double temperature;
    public String weatherDescription;
    public WeatherPanel(){
        setLayout(new FlowLayout());

        weatherLabel = new JLabel("Weather: ");
        temperatureLabel = new JLabel("Temperature: ");

        add(weatherLabel);
        add(temperatureLabel);

        try {
            String apiKey = "388edff619a8463cbaf74088ced200f4"; // Weatherbit API 키
            String apiUrl = "https://api.weatherbit.io/v2.0/current?city=Seoul&key=" + apiKey;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // API 응답에서 온도와 날씨 정보 추출
            JSONObject jsonResponse = null;
            try {
                jsonResponse = new JSONObject(response.toString());
                temperature = jsonResponse.getJSONArray("data")
                        .getJSONObject(0)
                        .getDouble("temp");
                weatherDescription = jsonResponse.getJSONArray("data")
                        .getJSONObject(0)
                        .getJSONObject("weather")
                        .getString("description");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
