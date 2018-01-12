package mock;

/**
 * Created by Asiman. Crested on 12.01.2018.
 */
class WeatherForecastRequest {
    private String city;
    private String country;
    private String metric;

    WeatherForecastRequest(String city, String country) {
        this.city = city;
        this.country = country;
        this.metric = "metric";
    }

    String getCity() { return this.city; }

    String getCountry() { return this.country; }

    String getUnits() { return this.metric; }
}