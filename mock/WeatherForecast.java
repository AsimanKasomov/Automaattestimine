package mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asiman. Created on 12.02.2018.
 */
public class WeatherForecast {
    private double temp;
    private double minTemp;
    private double maxTemp;
    private Date date;
    private String city;
    private String country;
    private String coordinates;

    double getMinTemp() {
        return minTemp;
    }

    void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    double getMaxTemp() {
        return maxTemp;
    }

    void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    Date getDate() { return date; }

    String getDateString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd.MM.yyyy");
        return outputFormatter.format(date);
    }

    void setDate(Date date) {
        this.date = date;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    String getCoordinates() {
        return coordinates;
    }

    void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    double getTemp() {
        return temp;
    }

    void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return getDateString() + " Weather forecast in " + city + ',' + country + ", coordinates: '" +
                coordinates + "': " + "Temperature: " + temp + ", min:" + minTemp + ", max:" + maxTemp;
    }
}