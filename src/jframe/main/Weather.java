package jframe.main;

import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static jframe.main.GaebalTalk.weatherIcon;

public class Weather  {

    public double temperature;
    public String weatherDescription;
    public Weather(){
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


    public void weatherAPI(){
        if (weatherDescription.equals("Clear sky")) {

           weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/clearsky.png"));
        } else if (weatherDescription.equals("Mist") || weatherDescription.equals("Fog") ||
                weatherDescription.equals("Haze") || weatherDescription.equals("Smoke")) {
            weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/mist.png"));
        } else if (weatherDescription.equals("Cloudy") || weatherDescription.equals("Overcast clouds")) {
            weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/cloudy.png"));
        } else if (weatherDescription.equals("Few clouds") || weatherDescription.equals("Scattered clouds") ||
                weatherDescription.equals("Broken clouds")) {
            weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/fewcl.png"));
        } else if (weatherDescription.equals("Shower rain") || weatherDescription.equals("Rain") ||
                weatherDescription.equals("Thunderstorm")) {
            weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/sgiw.png"));
        } else if (weatherDescription.equals("Snow") || weatherDescription.equals("Sleet") ||
                weatherDescription.equals("Freezing rain")) {
            weatherIcon.setIcon(new ImageIcon("/Applications/fian/real/image/weatherAPI/sbow.png"));
        }
    }
}
