import java.io.IOException;
import data.WeatherData;

public class Starter {
    public static void main(String[] args) throws IOException {
        WeatherData weatherData = new WeatherData("Tallinn", "metric");
        System.out.println(weatherData.getLongitude());
    }
}
