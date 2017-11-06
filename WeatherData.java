package data;

import com.google.gson.JsonElement;
import java.net.HttpURLConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.io.IOException;
import java.io.InputStream;
import http.HttpUtility;
import java.util.*;

public class WeatherData {
    private JsonObject weatherDataJson;

    public WeatherData(String city, String units) throws IOException {
        getJsonResponse(city, units);
    }

    public List<BigDecimal> getCoordinates() {
        List<BigDecimal> coordinates = new ArrayList<>();
        coordinates.add(getLatitude());
        coordinates.add(getLongitude());
        return coordinates;
    }

    public Map getThreeDayForecast(double[] maxTemps, double[] minTemps) {
        Map<String, double[]> forecast = new HashMap<>();
        forecast.put("maxTemps", maxTemps);
        forecast.put("minTemps", minTemps);
        return forecast;
    }

    public BigDecimal getLongitude() {
        String longitude = weatherDataJson.get("city").getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString();
        return new BigDecimal(longitude);
    }

    public BigDecimal getLatitude() {
        String latitude = weatherDataJson.get("city").getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString();
        return new BigDecimal(latitude);
    }

    public BigDecimal getCurrentTemperature() {
        String currentTemp = weatherDataJson.getAsJsonArray("list").get(0).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsString();
        return new BigDecimal(currentTemp);
    }

    public JsonArray getThreeDayWeatherResponses() {
        int newDayStartIndex = 0;
        for (int i = 0; i < 8; i++) {
            if (getTimeOfElement(i).equals("0:00:00")) {
                newDayStartIndex = i;
                break;
            }
        }
        JsonArray threeDayWeather = new JsonArray();
        for (int i = 0; i < 24; i++) threeDayWeather.add(weatherDataJson.getAsJsonArray("list").get(newDayStartIndex + i));
        return threeDayWeather;
    }

    public double[] getMaxTemperatures() {
        double[] maxTempsOfEachDay = new double[3];
        for (int i = 0; i < 3;i++) {
            double highest = 0;
            for (int j = 0; j < 7; j++) {
                if (getThreeDayWeatherResponses().get((i + 1) * j).getAsJsonObject().get("main").getAsJsonObject().get("temp_max").getAsDouble() > highest) {
                    highest = getThreeDayWeatherResponses().get((i + 1) * j).getAsJsonObject().get("main").getAsJsonObject().get("temp_max").getAsDouble();
                }
            }
            maxTempsOfEachDay[i] = highest;
        }
        return maxTempsOfEachDay;
    }

    public double[] getMinTemperatures() {
        double[] minTempsOfEachDay = new double[3];
        for (int i = 0; i < 3;i++) {
            double lowest = getThreeDayWeatherResponses().get((i + 1)).getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsDouble();
            for (int j = 0; j < 7; j++) {
                if (getThreeDayWeatherResponses().get((i + 1) * j).getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsDouble() < lowest) {
                    lowest = getThreeDayWeatherResponses().get((i + 1) * j).getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsDouble();
                }
            }
            minTempsOfEachDay[i] = lowest;
        }
        return minTempsOfEachDay;
    }

    public JsonObject getJsonResponse(String city, String units) throws IOException {
        String requestUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=" + units + "&APPID=c2248ce1be70c38c1e89c712f76ef08e";
        HttpURLConnection request = HttpUtility.makeHttpGetRequest(requestUrl);
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        this.weatherDataJson = root.getAsJsonObject();
        return root.getAsJsonObject();
    }

    public String getTimeOfElement(int listElementNr) {
        DateFormat df = DateFormat.getDateTimeInstance();
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = df.format(new Date(weatherDataJson.get("list").getAsJsonArray().get(listElementNr).getAsJsonObject().get("dt").getAsLong() * 1000));
        return time.substring(time.indexOf(" ") + 1);
    }
}